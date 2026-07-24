package website_auditor.service;

import org.springframework.stereotype.Service;
import website_auditor.dto.AuditResponse;
import website_auditor.exception.InvalidUrlException;
import website_auditor.exception.WebsiteAuditException;
import website_auditor.util.HtmlParserUtil;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class AuditService {

    public AuditResponse auditWebsite(String url) {

        URI uri;

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        try {
            uri = URI.create(url);
        } catch (IllegalArgumentException e) {
            throw new InvalidUrlException("Invalid URL.");
        }

        if (uri.getScheme() == null ||
                (!uri.getScheme().equalsIgnoreCase("http")
                        && !uri.getScheme().equalsIgnoreCase("https"))) {

            throw new InvalidUrlException(
                    "URL must start with http:// or https://");
        }

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {

            long startTime = System.currentTimeMillis();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());


            long endTime = System.currentTimeMillis();

            String contentType = response.headers()
                    .firstValue("Content-Type")
                    .orElse("");

            if (!contentType.toLowerCase().contains("text/html")) {
                throw new WebsiteAuditException("The URL does not point to an HTML page.");
            }

            return HtmlParserUtil.parse(
                    response.body(),
                    url,
                    response.statusCode(),
                    endTime - startTime
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            throw new WebsiteAuditException("Request was interrupted.");

        } catch (IOException e) {

            throw new WebsiteAuditException("Failed to fetch the website.");
        }

    }
}
package website_auditor.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import website_auditor.dto.AuditResponse;

public class HtmlParserUtil {

    public static AuditResponse parse(
            String html,
            String url,
            int status,
            long responseTimeMs
    ) {

        Document document = Jsoup.parse(html);

        String title = document.title();

        Element metaTag = document.selectFirst("meta[name=description]");

        String metaDescription = metaTag != null
                ? metaTag.attr("content")
                : "";

        int h1Count = document.select("h1").size();

        int imagesWithoutAlt = 0;

        for (Element image : document.select("img")) {
            if (!image.hasAttr("alt") || image.attr("alt").isBlank()) {
                imagesWithoutAlt++;
            }
        }

        String text = document.body() != null
                ? document.body().text()
                : "";

        int wordCount = text.isBlank()
                ? 0
                : text.trim().split("\\s+").length;

        return AuditResponse.builder()
                .url(url)
                .status(status)
                .responseTimeMs(responseTimeMs)
                .title(title)
                .metaDescription(metaDescription)
                .h1Count(h1Count)
                .imagesWithoutAlt(imagesWithoutAlt)
                .wordCount(wordCount)
                .build();
    }
}
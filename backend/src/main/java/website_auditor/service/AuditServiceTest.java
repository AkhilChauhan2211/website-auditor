package website_auditor.service;

import org.junit.jupiter.api.Test;
import website_auditor.dto.AuditResponse;
import website_auditor.exception.InvalidUrlException;
import website_auditor.exception.WebsiteAuditException;

import static org.junit.jupiter.api.Assertions.*;

class AuditServiceTest {

    private final AuditService auditService = new AuditService();

    @Test
    void shouldAuditGoogleSuccessfully() {

        AuditResponse response = auditService.auditWebsite("https://google.com");

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertNotNull(response.getTitle());
        assertFalse(response.getTitle().isEmpty());

    }

    @Test
    void shouldThrowExceptionForUnreachableWebsite() {

        WebsiteAuditException exception =
                assertThrows(
                        WebsiteAuditException.class,
                        () -> auditService.auditWebsite("abcxyz123.invalid")
                );

        assertEquals(
                "Failed to fetch the website.",
                exception.getMessage()
        );

    }

    @Test
    void shouldThrowExceptionForNonHtmlPage() {

        WebsiteAuditException exception =
                assertThrows(
                        WebsiteAuditException.class,
                        () -> auditService.auditWebsite("https://httpbin.org/image/png")
                );

        assertEquals(
                "The URL does not point to an HTML page.",
                exception.getMessage()
        );

    }

}
package website_auditor.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditResponse {

    private String url;
    private int status;
    private long responseTimeMs;
    private String title;
    private String metaDescription;
    private int h1Count;
    private int imagesWithoutAlt;
    private int wordCount;

}

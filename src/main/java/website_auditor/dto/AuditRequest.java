package website_auditor.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditRequest {

    @NotBlank(message = "URL cannot be blank")
    private String url;

}
package website_auditor.controller;

import website_auditor.dto.AuditRequest;
import website_auditor.dto.AuditResponse;
import website_auditor.service.AuditService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping("/audit")
    public AuditResponse audit(@Valid @RequestBody AuditRequest request) {
        return auditService.auditWebsite(request.getUrl());
    }
}
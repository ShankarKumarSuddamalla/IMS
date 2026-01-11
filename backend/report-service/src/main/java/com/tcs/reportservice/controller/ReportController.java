package com.tcs.reportservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.reportservice.services.ReportService;
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/full")
    public ResponseEntity<byte[]> fullReport() throws Exception {

        byte[] pdf = service.generateFullReport();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdf);
    }
}

package io.assignment.DynamicPdfGenerator.pdfgenerator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.assignment.DynamicPdfGenerator.pdfgenerator.dtos.InvoiceRequest;
import io.assignment.DynamicPdfGenerator.pdfgenerator.exceptions.DocumentGenerationException;
import io.assignment.DynamicPdfGenerator.pdfgenerator.services.PdfService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/api/v1/pdf")
class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<Resource> generatePdf(@NotNull @RequestBody InvoiceRequest request) throws DocumentGenerationException, NoSuchAlgorithmException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        var resource = pdfService.generateOrFetchPdf(request);
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
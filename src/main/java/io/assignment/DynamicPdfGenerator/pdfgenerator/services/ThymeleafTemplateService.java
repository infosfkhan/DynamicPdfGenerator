package io.assignment.DynamicPdfGenerator.pdfgenerator.services;



import io.assignment.DynamicPdfGenerator.pdfgenerator.dtos.InvoiceRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ThymeleafTemplateService {
    private final TemplateEngine templateEngine;

    public ThymeleafTemplateService(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }
    public String parseThymeleafTemplate(String template, InvoiceRequest templateData) {
        Context context = new Context();
        context.setVariable("data", templateData);
        return templateEngine.process(template, context);
    }
}

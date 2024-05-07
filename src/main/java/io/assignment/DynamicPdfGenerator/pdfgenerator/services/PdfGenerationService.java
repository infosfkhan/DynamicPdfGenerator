package io.assignment.DynamicPdfGenerator.pdfgenerator.services;



import com.lowagie.text.DocumentException;
import io.assignment.DynamicPdfGenerator.pdfgenerator.dtos.InvoiceRequest;
import io.assignment.DynamicPdfGenerator.pdfgenerator.exceptions.DocumentGenerationException;
import io.assignment.DynamicPdfGenerator.utils.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.AbstractMap;

@Service
public class PdfGenerationService {

    @Value("${output.folder}")
    private String outputFolder;

    @Autowired
    EncoderService encoderService;

    public PdfGenerationService() {}
    public String generatePdfFromHtml(String html, InvoiceRequest request) throws DocumentGenerationException {
        String uniqueUuid = UuidGenerator.generateUuid();
        String outputFilePath = outputFolder + File.separator + uniqueUuid + ".pdf";

        try (OutputStream outputStream = new FileOutputStream(outputFilePath)) {
            ITextRenderer renderer = createPdfRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            PdfMetaDataService.savePdfMetadata(new AbstractMap.SimpleEntry<>(encoderService.encodeToSHA256(request),outputFilePath), outputFolder);
            return outputFilePath;
        }
        catch (FileNotFoundException e) {
            throw new DocumentGenerationException("Output file not found: " + outputFilePath, e);
        } catch (IOException e) {
            throw new DocumentGenerationException("Error occurred while writing PDF", e);
        } catch (DocumentException e) {
            throw new DocumentGenerationException("Error occurred while creating PDF", e);
        } catch (Exception e) {
            throw new DocumentGenerationException("An unexpected error occurred");
        }
    }

    private ITextRenderer createPdfRenderer() {
        return new ITextRenderer();
    }


}





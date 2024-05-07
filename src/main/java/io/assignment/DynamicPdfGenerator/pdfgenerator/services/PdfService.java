package io.assignment.DynamicPdfGenerator.pdfgenerator.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.assignment.DynamicPdfGenerator.pdfgenerator.dtos.InvoiceRequest;
import io.assignment.DynamicPdfGenerator.pdfgenerator.exceptions.DocumentGenerationException;
import io.assignment.DynamicPdfGenerator.pdfgenerator.exceptions.DocumentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;


@Service
public class PdfService {


    @Autowired
    private ThymeleafTemplateService thymeleafTemplateService;
    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private PdfMetaDataWatcher pdfMetaDataWatcher;

    @Autowired
    private EncoderService encoderService;
    public Resource generateOrFetchPdf(InvoiceRequest request) throws DocumentGenerationException, NoSuchAlgorithmException, JsonProcessingException {

        String encoded = encoderService.encodeToSHA256(request);
        if(pdfMetaDataWatcher.getMetadataMap().containsKey(encoded)){
            var path = pdfMetaDataWatcher.getMetadataMap().get(encoded);
            Resource resource = null;
            try {
                resource = new UrlResource(Paths.get(path).toUri());
            } catch (MalformedURLException e) {
//                throw new RuntimeException(e); // not trying to throw any exception. TODO: ADD loggers.
                System.out.println("malformed string");
            }
            if(resource!=null && resource.exists()) {
                return resource;
            }
        }

        var path = pdfGenerationService.generatePdfFromHtml(thymeleafTemplateService.parseThymeleafTemplate("invoice",request), request);
        try {
//            TimeUnit.SECONDS.sleep(1);
            Resource resource = new UrlResource(Paths.get(path).toUri());
            if(resource.exists()){
                return resource;
            } else {
                throw new DocumentNotFoundException("Resource does not exists");
            }
        } catch (MalformedURLException e) {
            throw new DocumentNotFoundException("Invalid file URL", e);
        }
//        catch (InterruptedException e) { throw new DocumentNotFoundException("Thread interrupted before creating document", e);}
    }
}


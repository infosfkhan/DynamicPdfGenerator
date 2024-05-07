package io.assignment.DynamicPdfGenerator.pdfgenerator.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PdfMetaDataService {
    public static void savePdfMetadata(Map.Entry<String, String> metadata, String outputFolder) throws IOException {
        File metadataFile = new File(outputFolder + File.separator + "pdf-metadata.json");

        Map<String, String> existingMetadata = new HashMap<>();
        if (metadataFile.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            existingMetadata = objectMapper.readValue(metadataFile, new TypeReference<Map<String, String>>() {});
        }
        existingMetadata.put(metadata.getKey(), metadata.getValue());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(metadataFile, existingMetadata);
    }
}


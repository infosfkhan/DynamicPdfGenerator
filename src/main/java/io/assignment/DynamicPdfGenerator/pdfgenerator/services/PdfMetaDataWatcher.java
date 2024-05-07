package io.assignment.DynamicPdfGenerator.pdfgenerator.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfMetaDataWatcher {
    private Map<String, String> metadataMap = new HashMap<>();
    @Value("${output.folder}")
    private String outputFolder;

    @PostConstruct
    public void initialize() {
        initializeMetadata();
        startMonitoring();
    }

    private void initializeMetadata() {


        File metadataFile = new File(outputFolder + File.separator + "pdf-metadata.json");
        if (metadataFile.exists()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                metadataMap = objectMapper.readValue(metadataFile, new TypeReference<Map<String, String>>() {});
            } catch (IOException e) {
                e.printStackTrace(); //TODO : Handle error appropriately
            }
        }
    }

    private void startMonitoring() {
//        Path directory = Paths.get(outputFolder, "pdf-metadata.json");
        Path directory = Paths.get(outputFolder);
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            new Thread(() -> {
                while (true) {
                    WatchKey key;
                    try {
                        key = watchService.take();
                    } catch (InterruptedException e) {
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == StandardWatchEventKinds.OVERFLOW) {
                            continue;
                        }

                        @SuppressWarnings("unchecked")
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path filename = ev.context();
                        if (filename.toString().equals("pdf-metadata.json")) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            initializeMetadata();
                            break;
                        }
                    }

                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace(); //TODO : Handle error appropriately
        }
    }

    public Map<String, String> getMetadataMap() {
        return metadataMap;
    }




}


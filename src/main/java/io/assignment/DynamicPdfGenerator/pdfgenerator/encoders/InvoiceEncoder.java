package io.assignment.DynamicPdfGenerator.pdfgenerator.encoders;


import io.assignment.DynamicPdfGenerator.utils.HashStrategy;

import java.util.HashMap;
import java.util.UUID;

public class InvoiceEncoder {
    private final HashMap<UUID,String> encodedObjects;
    private final HashStrategy hashStrategy;

    public InvoiceEncoder(HashStrategy hashStrategy) {
        encodedObjects = new HashMap<>();
        this.hashStrategy = hashStrategy;
    }

    public boolean isEncoded(Object obj) {
        String hash = hashStrategy.generateHash(obj);
        return encodedObjects.containsValue(hash);
    }

    public void encodeAndStore(UUID uuid, Object obj) {
        String hash = hashStrategy.generateHash(obj);
        encodedObjects.putIfAbsent(uuid,hash);
    }
}
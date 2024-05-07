package io.assignment.DynamicPdfGenerator.utils;

import io.assignment.DynamicPdfGenerator.utils.HashStrategy;
import org.springframework.context.annotation.Primary;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


// TODO: We can add strategy pattern later if different Hash Strategy is required
@Primary
public class SHA256HashStrategy implements HashStrategy {

    @Override
    public String generateHash(Object obj) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(obj.toString().getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

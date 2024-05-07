package io.assignment.DynamicPdfGenerator.pdfgenerator.exceptions;


public class DocumentGenerationException extends Exception {

    public DocumentGenerationException(String message) {
        super(message);
    }

    public DocumentGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}

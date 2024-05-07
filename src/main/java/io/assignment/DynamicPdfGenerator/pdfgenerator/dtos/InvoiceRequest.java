package io.assignment.DynamicPdfGenerator.pdfgenerator.dtos;

import java.util.List;

public class InvoiceRequest {
    public String seller;
    public String sellerGstin;
    public String sellerAddress;
    public String buyer;
    public String buyerGstin;
    public String buyerAddress;
    public List<Item> items;

    @Override
    public String toString() {
        return "InvoiceRequest{" +
                "seller='" + seller + '\'' +
                ", sellerGstin='" + sellerGstin + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerGstin='" + buyerGstin + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", items=" + items +
                '}';
    }
}


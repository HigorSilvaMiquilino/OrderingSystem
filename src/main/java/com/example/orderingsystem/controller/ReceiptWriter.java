package com.example.orderingsystem.controller;


import com.example.orderingsystem.ProductData;
import com.example.orderingsystem.data;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReceiptWriter {
    public void writeReceiptToPDF(List<ProductData> orderData, double total, double change) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        String filename = "receipt_" + timeStamp + ".pdf";
        String path = "C:\\Users\\Cliente\\Desktop\\" + filename;

        Document documentPdf = new Document();

        try {
            PdfWriter.getInstance(documentPdf, new FileOutputStream(path));
            documentPdf.open();

            Paragraph titleParagraph = new Paragraph();
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            titleParagraph.add(new Chunk("ORDERING APP", new Font(Font.HELVETICA, 24)));
            documentPdf.add(titleParagraph);

            documentPdf.add(new Paragraph(" "));

            Paragraph paragraphDate = new Paragraph();
            paragraphDate.setAlignment(Element.ALIGN_CENTER);
            paragraphDate.add(new Chunk("Date: " + time,new Font(Font.HELVETICA, 12)));
            documentPdf.add(paragraphDate);
            documentPdf.add(new Paragraph(" "));

            Paragraph clientParagraph = new Paragraph();
            clientParagraph.setAlignment(Element.ALIGN_CENTER);
            clientParagraph.add(new Chunk("Client: " + data.username, new Font(Font.BOLD, 16)));
            documentPdf.add(clientParagraph);


            Paragraph sessionParagraph = new Paragraph("----------------------------------------------------------");
            sessionParagraph.setAlignment(Element.ALIGN_CENTER);
            documentPdf.add((sessionParagraph));
            documentPdf.add((new Paragraph(" ")));


            Paragraph orderParagraph = new Paragraph();
            orderParagraph.setAlignment(Element.ALIGN_CENTER);
            orderParagraph.add(new Chunk("ORDER", new Font(Font.TIMES_ROMAN, 16)));
            documentPdf.add(new Paragraph(orderParagraph));
            documentPdf.add((new Paragraph(" ")));

            for (ProductData prod : orderData) {
                Paragraph orderInfo = new Paragraph();
                orderInfo.setAlignment(Element.ALIGN_CENTER);
                orderInfo.add("Product Name: " + prod.getProductName() + "\n");
                orderInfo.add("Type: " + prod.getType() + "\n");
                orderInfo.add("Quantity: " + prod.getQuantity() + "\n");
                orderInfo.add("Price: $" + prod.getPrice() + "\n");
                orderInfo.add("------------------------------\n");
                documentPdf.add(orderInfo);
            }

            documentPdf.add((new Paragraph(" ")));

            Paragraph paragraphTotal = new Paragraph();
            paragraphTotal.setAlignment(Element.ALIGN_CENTER);
            paragraphTotal.add(new Chunk("Change: $" + change + "\n"+ "\n"
                    + "Total: $" + total + "\n", new Font(Font.TIMES_ROMAN, 16)));
            documentPdf.add(paragraphTotal);
            documentPdf.add((new Paragraph(" ")));

            Paragraph footerSessionParagraph = new Paragraph("----------------------------------------------------------");
            footerSessionParagraph.setAlignment(Element.ALIGN_CENTER);
            documentPdf.add(footerSessionParagraph);
            documentPdf.add(new Paragraph(" "));
            Paragraph footerParagraph = new Paragraph();
            footerParagraph.setAlignment(Element.ALIGN_CENTER);
            footerParagraph.add(new Chunk("THANK YOU", new Font(Font.TIMES_ROMAN, 14)));
            documentPdf.add((footerParagraph));


            System.out.println("Receipt written to " + filename + " " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        documentPdf.close();
    }
}

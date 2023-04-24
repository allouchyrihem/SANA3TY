/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author HP
 *//*
public class Facture {
   private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    public static void generateInvoice() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));
        document.open();
        document.add(new Paragraph("INVOICE"));
        document.add(new Paragraph("Date: " + dateFormat.format(new Date())));
        document.add(new Paragraph("Customer Name: John Doe"));
        document.add(new Paragraph("Address: 123 Main St."));
        document.add(new Paragraph("Total: $100"));
        document.close();
    } 
}
*/
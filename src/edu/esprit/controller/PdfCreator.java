/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entity.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author nada
 */
public class PdfCreator {
     public void createPDF(Reclamation reclamation) throws FileNotFoundException, BadElementException, IOException {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("reclamation.pdf"));

        document.open();           

         // Add image
        Image image = Image.getInstance("C:\\Users\\asus\\Documents\\NetBeansProjects\\chance\\src\\edu\\esprit\\assets\\ogo.png");
        image.scaleAbsolute(200, 200); // Resize image if needed
        document.add(image);
         // Add the title here
    Paragraph title = new Paragraph("Rapport de  Reclamation", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, BaseColor.BLACK));
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);
     // Add an empty paragraph for spacing
    document.add(new Paragraph(" "));
    document.add(new Paragraph(" "));
        document.add(new Paragraph("Sujet: " + reclamation.getSujet()));
        document.add(new Paragraph("Description: " + reclamation.getDescription()));
         // Add current date
        LocalDate currentDate = LocalDate.now();
        document.add(new Paragraph("Date: " + currentDate));
        document.close();
    } catch (DocumentException | FileNotFoundException e) {
    }
}
}
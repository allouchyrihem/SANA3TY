/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entity.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author nada
 */
public class PdfCreator {
     public void createPDF(Reclamation reclamation) throws FileNotFoundException {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("reclamation.pdf"));
        document.open();
        document.add(new Paragraph("Sujet: " + reclamation.getSujet()));
        document.add(new Paragraph("Description: " + reclamation.getDescription()));
        document.close();
    } catch (DocumentException | FileNotFoundException e) {
    }
}
}

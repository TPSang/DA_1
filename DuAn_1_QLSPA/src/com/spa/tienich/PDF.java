/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.tienich;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JTable;

public class PDF {

    private static String FILE = "./pdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void taoFilePdf(String tenkh, JTable jtable , int n,String tien,String ngaydl) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            //
            Paragraph preface = new Paragraph();
            // We add one empty line
            addEmptyLine(preface, 1);
            // Lets write a big header
            preface.add(new Paragraph("Cam on khach hang "+tenkh, catFont));

            addEmptyLine(preface, 1);
            // Will create: Report generated by: _name, _date
            preface.add(new Paragraph(
                    "Beauty Spa, " + new Date(),
                    smallBold));
            addEmptyLine(preface, 0);
            preface.add(new Paragraph(
                    "-Ban da dat hang thanh cong, day la hoa don cua ban ",
                    smallBold));

            addEmptyLine(preface, 2);
            //
            PdfPTable t = new PdfPTable(4);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);

            PdfPCell c1 = new PdfPCell(new Phrase("Ten dv/sp"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Gia"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("So luong"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Tong"));
            t.addCell(c4);
            
            for (int i=0;i<n;i++){
                String gia=Integer.toString((int)jtable.getValueAt(i, 1));
                String sl=Integer.toString((int)jtable.getValueAt(i, 2));
                String tong=Integer.toString((int)jtable.getValueAt(i, 3));
                t.addCell((String)jtable.getValueAt(i, 0));
                t.addCell(gia);
                t.addCell(sl);
                t.addCell(tong);
            }
           
            preface.add(t);
            addEmptyLine(preface, 1);
            //
            preface.add(new Paragraph("Tong tien = "+tien, catFont));

            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Ngay dat lich : "+ngaydl));
            preface.add(new Paragraph(
                    "Vui long giu hoa don nay khi den lam dep",
                    redFont));

            document.add(preface);
            // Start a new page
//        document.newPage();
            //
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

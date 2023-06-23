package com.farmacia.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
public class GenerdorDocumentos {

	
	
	public void generarPDFs(String codigo, int cantidad)  {
		
		try {
			Document doc=new Document();
			JFileChooser selectCarpeta=new JFileChooser();
			selectCarpeta.setCurrentDirectory(new File("."));
			PdfWriter pdf=PdfWriter.getInstance(doc, new FileOutputStream("/Users/janpierarmijos/Desktop/codigo.pdf"));
			pdf.open();
			doc.open();
			BarcodeEAN code=new BarcodeEAN();
			code.setCode(codigo);
			Image img=code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
			
			 PdfPTable table = new PdfPTable(2);
			 PdfPCell cell1 = new PdfPCell(img);
			 for (int i = 0; i <= cantidad; i++) {
				  cell1 = new PdfPCell(img);
				 cell1.setPadding(10);
				 
				 table.addCell(cell1);
			}
			 cell1 = new PdfPCell(img);
			 cell1.setPadding(10);
			 table.addCell(cell1);
			
		doc.add(table);
		
		doc.close();
		pdf.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}

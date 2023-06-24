package com.farmacia.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

import javax.swing.JFileChooser;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
public class GenerdorDocumentos {

	
	
	public void generarPDFs(String codigo, int cantidad,String carpetaSeleccionada)  {
		
		try {
			//BufferedWriter writer=Files.newBufferedWriter(Paths.get(carpetaSeleccionada.getAbsolutePath()+File.separator+"codigo.pdf"));

			//String h=Paths.get(carpetaSeleccionada.getAbsolutePath()+File.separator+"codigo.pdf").toString();
			LocalDateTime hora = LocalDateTime.now();
	        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        //System.out.println(hora.format(f));
			String h=carpetaSeleccionada.replaceAll( Matcher.quoteReplacement(File.separator), "/")+"/codigo"+hora.format(f)+".pdf";
			//System.out.println("Desde metodo "+h);
			Document doc=new Document(PageSize.A4);
			
			PdfWriter pdf=PdfWriter.getInstance(doc, new FileOutputStream(h));
			pdf.open();
			doc.open();
			Barcode39 code=new Barcode39();
			code.setCode(codigo);
			Image img=code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
			//img.scaleToFit(100, 100);
			 PdfPTable table = new PdfPTable(2);
			 table.setWidthPercentage(100);
			 PdfPCell cell1 = new PdfPCell();
			 for (int i = 0; i <= cantidad; i++) {
				  cell1 = new PdfPCell(img);
				 cell1.setPadding(10);
				 
				 table.addCell(cell1);
			}
			
			
		doc.add(table);
		
		doc.close();
		pdf.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}

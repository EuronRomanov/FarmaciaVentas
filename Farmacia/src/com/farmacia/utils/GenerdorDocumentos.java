package com.farmacia.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerdorDocumentos {

	
	
	public void generarPDFs()  {
		
		try {
			Document doc=new Document();
			PdfWriter pdf=PdfWriter.getInstance(doc, new FileOutputStream("codigo.pdf"));
			BarcodeEAN code=new BarcodeEAN();
			code.setCode("123456789");
			Image img=code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
		doc.add(img);
		doc.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}

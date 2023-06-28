package com.farmacia.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

import javax.swing.JFileChooser;

import com.farmacia.bd.ConexionBD;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
public class GenerdorDocumentos {

	
	private static Connection con=ConexionBD.conectar();
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

	
	public void generarTicket(int idFactura) {
		Rectangle r=new Rectangle(210,400);
		
		Document document=new Document(r,9f,9f,7f,7f);
	try {
		FileOutputStream archivo=new FileOutputStream("ticket.pdf");
		PdfWriter.getInstance(document, archivo);
		document.open();
		//FUENTES
		Font fuenteTitulo=FontFactory.getFont(
				FontFactory.HELVETICA_BOLD,9,Font.NORMAL,
				BaseColor.BLACK);
		Font fuenteDescripcion=FontFactory.getFont(
				FontFactory.HELVETICA_BOLD,9,Font.NORMAL,
				BaseColor.BLACK);
		
		//SALTO LINEA
		Paragraph saltoLinea=new Paragraph();
		saltoLinea.add(new Paragraph(Chunk.NEWLINE));
		document.add(saltoLinea);
		
		PdfPTable encabezadoTicket=new PdfPTable(1);
		encabezadoTicket.setWidthPercentage(100);
		float[] medidaCelda= {100f};
		encabezadoTicket.setWidths(medidaCelda);
		
		PdfPCell empresa=new PdfPCell(new Phrase("EMPRESA DEMO", fuenteTitulo));
		empresa.setVerticalAlignment(Element.ALIGN_CENTER);
		empresa.setHorizontalAlignment(Element.ALIGN_CENTER);
		empresa.setBorder(0);
		PdfPCell direccion=new PdfPCell(new Phrase("Quito Ecuador", fuenteTitulo));
		 direccion.setVerticalAlignment(Element.ALIGN_CENTER);
		 direccion.setHorizontalAlignment(Element.ALIGN_CENTER);
		 direccion.setBorder(0);
		 PdfPCell rnc= new PdfPCell(new Phrase("RUC: 000000000000", fuenteTitulo));
		 rnc.setVerticalAlignment(Element.ALIGN_CENTER);
		 rnc.setHorizontalAlignment(Element.ALIGN_CENTER);
		 rnc.setBorder(0);
		 encabezadoTicket.addCell(empresa);
		 encabezadoTicket.addCell(direccion);
		 encabezadoTicket.addCell(rnc);
		 document.add(encabezadoTicket);
		 
		 
		 
		 PdfPTable encabezadoCliente=new PdfPTable(2);
			encabezadoTicket.setWidthPercentage(100);
			float[] medidaCeldaCliente= {20f,80f};
			encabezadoTicket.setWidths(medidaCeldaCliente);
			
			try {
				String query="";
				int contador=0;
				Statement st= con.createStatement();
				ResultSet rs=st.executeQuery(query);
				while(rs.next()) {
					contador++;
					if(contador<=1) {
						PdfPCell nombreCliente=new PdfPCell(new Phrase("Cliente", fuenteTitulo));
						nombreCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						nombreCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						nombreCliente.setBorder(0);
						PdfPCell datoCliente=new PdfPCell(new Phrase(rs.getString("nombre"), fuenteDescripcion));
						datoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						datoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						datoCliente.setBorder(0);
						 encabezadoCliente.addCell(nombreCliente);
						 encabezadoCliente.addCell(datoCliente);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			document.add(encabezadoCliente);
		 
		 document.close();
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
}

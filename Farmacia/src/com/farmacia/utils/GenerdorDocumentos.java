package com.farmacia.utils;

import java.awt.Desktop;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.swing.JFileChooser;

import com.farmacia.bd.ConexionBD;
import com.farmacia.controlador.DetalleDao;
import com.farmacia.controlador.FacturaDao;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Factura;
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
		
		Factura factura=new FacturaDao().searchFacturaId(idFactura);
		List<Detalle>listDetalles=new DetalleDao().listarDetallesId(idFactura);
		
		
		Rectangle r=new Rectangle(210,400);
		
		Document document=new Document(r,9f,9f,7f,7f);
	try {
		FileOutputStream archivo=new FileOutputStream("C:/Users/HP/Desktop/ticket.pdf");
		PdfWriter.getInstance(document, archivo);
		document.open();
		//FUENTES
		Font fuenteTitulo=FontFactory.getFont(
				FontFactory.HELVETICA_BOLD,8,Font.NORMAL,
				BaseColor.BLACK);
		Font fuenteDescripcion=FontFactory.getFont(
				FontFactory.HELVETICA_BOLD,7,Font.NORMAL,
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
			float[] medidaCeldaCliente= {35f,65f};
			encabezadoTicket.setWidths(medidaCeldaCliente);
			
			try {
				String query="";
				/*int contador=0;
				Statement st= con.createStatement();
				ResultSet rs=st.executeQuery(query);
				while(rs.next()) {
					contador++;
					if(contador<=1) {*/
						PdfPCell nombreCliente=new PdfPCell(new Phrase("Cliente", fuenteTitulo));
						nombreCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						nombreCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						nombreCliente.setBorder(0);
						PdfPCell datoCliente=new PdfPCell(new Phrase(factura.getUsuario(), fuenteDescripcion));
						datoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						datoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						datoCliente.setBorder(0);
						 encabezadoCliente.addCell(nombreCliente);
						 encabezadoCliente.addCell(datoCliente);
						 
						 
						 PdfPCell documentoCliente=new PdfPCell(new Phrase("Cliente", fuenteTitulo));
						 documentoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						 documentoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						 documentoCliente.setBorder(0);
							PdfPCell datoDocumentoCliente=new PdfPCell(new Phrase(factura.getRuc(), fuenteDescripcion));
							datoDocumentoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
							datoDocumentoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
							datoDocumentoCliente.setBorder(0);
							 encabezadoCliente.addCell(documentoCliente);
							 encabezadoCliente.addCell(datoDocumentoCliente);
					//}
				//}
			} catch (Exception e) {
				// TODO: handle exception
			}
			document.add(encabezadoCliente);
			
			//dato del detalle d la venta
			 PdfPTable ventas=new PdfPTable(5);
			 ventas.setWidthPercentage(100);
			 float[] medidaCeldaVentas= {15f,20f,15f,15f,15f};
			 ventas.setWidths(medidaCeldaVentas);
			
			 
			 PdfPCell cantidad=new PdfPCell(new Phrase("Cant", fuenteTitulo));
			 cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
			 cantidad.setHorizontalAlignment(Element.ALIGN_LEFT);
			 cantidad.setBorder(0);
			
			 PdfPCell medida=new PdfPCell(new Phrase("UM", fuenteTitulo));
			 medida.setVerticalAlignment(Element.ALIGN_CENTER);
			 medida.setHorizontalAlignment(Element.ALIGN_LEFT);
			 medida.setBorder(0);
			 
			 PdfPCell precio=new PdfPCell(new Phrase("PRECIO", fuenteTitulo));
			 precio.setVerticalAlignment(Element.ALIGN_CENTER);
			 precio.setHorizontalAlignment(Element.ALIGN_LEFT);
			 precio.setBorder(0);
			 
			 PdfPCell itbis=new PdfPCell(new Phrase("ITBIS", fuenteTitulo));
			 itbis.setVerticalAlignment(Element.ALIGN_CENTER);
			 itbis.setHorizontalAlignment(Element.ALIGN_LEFT);
			 itbis.setBorder(0);
			 
			 PdfPCell total=new PdfPCell(new Phrase("Total", fuenteTitulo));
			 total.setVerticalAlignment(Element.ALIGN_CENTER);
			 total.setHorizontalAlignment(Element.ALIGN_LEFT);
			 total.setBorder(0);
			 
			 ventas.addCell(cantidad);
			 ventas.addCell(medida);
			 ventas.addCell(itbis);
			 ventas.addCell(precio);
			 ventas.addCell(total);
			// document.add(ventas);
			 
			 
			 //consulta de producto
			 
			 try {
				 String query="";
					
					/*Statement st= con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next()) {*/
				 for (Detalle detalle : listDetalles) {
					
				
						 PdfPCell cantiidadDato=new PdfPCell(new Phrase(String.valueOf(detalle.getCantidad()), fuenteDescripcion));
						 cantiidadDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 cantiidadDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 cantiidadDato.setBorder(0);
						
						 PdfPCell medidaDato=new PdfPCell(new Phrase(detalle.getProducto(), fuenteDescripcion));
						 medidaDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 medidaDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 medidaDato.setBorder(0);
						 
						 PdfPCell precioDato=new PdfPCell(new Phrase(String.valueOf(detalle.getPrecioVenta()), fuenteDescripcion));
						 precioDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 precioDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 precioDato.setBorder(0);
						 
						 PdfPCell itbisDato=new PdfPCell(new Phrase(String.valueOf(detalle.getCodProducto()), fuenteDescripcion));
						 itbisDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 itbisDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 itbisDato.setBorder(0);
						 
						 PdfPCell totalDato=new PdfPCell(new Phrase(String.valueOf(detalle.getV_total()), fuenteDescripcion));
						 totalDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 totalDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 totalDato.setBorder(0);
						 
						 ventas.addCell(cantiidadDato);
						 ventas.addCell(medidaDato);
						 ventas.addCell(itbisDato);
						 ventas.addCell(precioDato);
						 ventas.addCell(totalDato);
						 
				 }	 
					//}
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
			 
			 //DATOS PIE  DE PAGINA
			 PdfPTable footer=new PdfPTable(2);
			 footer.setWidthPercentage(100);
			 float[] medidaCeldaFooter= {15f,20f};
			 footer.setWidths(medidaCeldaFooter);
			 
			 try {
				 String query="";
				/*	int contador=0;
					Statement st= con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next()) {
				 contador++;
					if(contador<=1) {*/
						
						 PdfPCell observacion=new PdfPCell(new Phrase("Observacion", fuenteTitulo));
						 observacion.setVerticalAlignment(Element.ALIGN_CENTER);
						 observacion.setHorizontalAlignment(Element.ALIGN_RIGHT);
						 observacion.setBorder(0);
							PdfPCell observacionDato=new PdfPCell(new Phrase(String.valueOf(factura.getObervacion()), fuenteDescripcion));
							observacionDato.setVerticalAlignment(Element.ALIGN_CENTER);
							observacionDato.setHorizontalAlignment(Element.ALIGN_LEFT);
							observacionDato.setBorder(0);
						
						PdfPCell subTotal=new PdfPCell(new Phrase("SubTotal", fuenteTitulo));
						subTotal.setVerticalAlignment(Element.ALIGN_CENTER);
						subTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
						subTotal.setBorder(0);
						PdfPCell subTotalDato=new PdfPCell(new Phrase(String.valueOf(factura.getSubtotal()), fuenteDescripcion));
						subTotalDato.setVerticalAlignment(Element.ALIGN_CENTER);
						subTotalDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						subTotalDato.setBorder(0);
						
						
						PdfPCell totalVentas=new PdfPCell(new Phrase("Total", fuenteTitulo));
						totalVentas.setVerticalAlignment(Element.ALIGN_CENTER);
						totalVentas.setHorizontalAlignment(Element.ALIGN_RIGHT);
						totalVentas.setBorder(0);
						PdfPCell totalVentasDato=new PdfPCell(new Phrase(String.valueOf(factura.getTotal()), fuenteDescripcion));
						totalVentasDato.setVerticalAlignment(Element.ALIGN_CENTER);
						totalVentasDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						totalVentasDato.setBorder(0);
						
						
						
						 footer.addCell(observacion);
						 footer.addCell(observacionDato);
						 footer.addCell(subTotal);
						 footer.addCell(subTotalDato);
						 footer.addCell(totalVentas);
						 footer.addCell(totalVentasDato);
						 
						
							
					//}
					//}
			} catch (Exception e) {
				// TODO: handle exception
			}
			 document.add(saltoLinea);
			 document.add(ventas);
			 document.add(footer);
		 document.close();
		 
		 
		 File abrirArchivo=new File("C:/Users/HP/Desktop/ticket.pdf");
		 Desktop.getDesktop().open(abrirArchivo);
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
	
public void generarTicketTest(int idFactura) {
	LocalDateTime fecha=LocalDateTime.now();
		Factura factura=new Factura(1,  fecha, "dsddsd", "", "Pepe Mujica", "",
				0, 135, "Leonardo Fabio");
		List<Detalle>listDetalles=new ArrayList();
		listDetalles.add(new Detalle(1, 4, "Ibuprofeno", 3.5, 4.5));
		
		Rectangle r=new Rectangle(210,400);
		
		Document document=new Document(r,9f,9f,7f,7f);
	try {
		FileOutputStream archivo=new FileOutputStream("C:/Users/HP/Desktop/ticket.pdf");
		PdfWriter.getInstance(document, archivo);
		document.open();
		//FUENTES
		Font fuenteTitulo=FontFactory.getFont(
				FontFactory.HELVETICA_BOLD,8,Font.NORMAL,
				BaseColor.BLACK);
		Font fuenteDescripcion=FontFactory.getFont(
				FontFactory.HELVETICA_BOLD,7,Font.NORMAL,
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
		 encabezadoCliente.setWidthPercentage(100);
			float[] medidaCeldaCliente= {35f,65f};
			encabezadoCliente.setWidths(medidaCeldaCliente);
			
		
				
			
						PdfPCell nombreCliente=new PdfPCell(new Phrase("Cliente", fuenteTitulo));
						nombreCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						nombreCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						nombreCliente.setBorder(0);
						PdfPCell datoCliente=new PdfPCell(new Phrase(factura.getUsuario(), fuenteDescripcion));
						datoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						datoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						datoCliente.setBorder(0);
						 encabezadoCliente.addCell(nombreCliente);
						 encabezadoCliente.addCell(datoCliente);
						 
						 
						 PdfPCell documentoCliente=new PdfPCell(new Phrase("Ruc", fuenteTitulo));
						 documentoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						 documentoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						 documentoCliente.setBorder(0);
							PdfPCell datoDocumentoCliente=new PdfPCell(new Phrase(factura.getRuc(), fuenteDescripcion));
							datoDocumentoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
							datoDocumentoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
							datoDocumentoCliente.setBorder(0);
							 encabezadoCliente.addCell(documentoCliente);
							 encabezadoCliente.addCell(datoDocumentoCliente);
				
			
			document.add(encabezadoCliente);
			
			//dato del detalle d la venta
			 PdfPTable ventas=new PdfPTable(5);
			 ventas.setWidthPercentage(100);
			 float[] medidaCeldaVentas= {15f,20f,15f,15f,15f};
			 ventas.setWidths(medidaCeldaVentas);
			
			 
			 PdfPCell cantidad=new PdfPCell(new Phrase("Cant", fuenteTitulo));
			 cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
			 cantidad.setHorizontalAlignment(Element.ALIGN_LEFT);
			 cantidad.setBorder(0);
			
			 PdfPCell medida=new PdfPCell(new Phrase("UM", fuenteTitulo));
			 medida.setVerticalAlignment(Element.ALIGN_CENTER);
			 medida.setHorizontalAlignment(Element.ALIGN_LEFT);
			 medida.setBorder(0);
			 
			 PdfPCell precio=new PdfPCell(new Phrase("PRECIO", fuenteTitulo));
			 precio.setVerticalAlignment(Element.ALIGN_CENTER);
			 precio.setHorizontalAlignment(Element.ALIGN_LEFT);
			 precio.setBorder(0);
			 
			 PdfPCell itbis=new PdfPCell(new Phrase("ITBIS", fuenteTitulo));
			 itbis.setVerticalAlignment(Element.ALIGN_CENTER);
			 itbis.setHorizontalAlignment(Element.ALIGN_LEFT);
			 itbis.setBorder(0);
			 
			 PdfPCell total=new PdfPCell(new Phrase("Total", fuenteTitulo));
			 total.setVerticalAlignment(Element.ALIGN_CENTER);
			 total.setHorizontalAlignment(Element.ALIGN_LEFT);
			 total.setBorder(0);
			 
			 ventas.addCell(cantidad);
			 ventas.addCell(medida);
			 ventas.addCell(itbis);
			 ventas.addCell(precio);
			 ventas.addCell(total);
			 // document.add(ventas);
			 
			 
			 //consulta de producto
			 
			
				 for (Detalle detalle : listDetalles) {
					
				
						 PdfPCell cantiidadDato=new PdfPCell(new Phrase(String.valueOf(detalle.getCantidad()), fuenteDescripcion));
						 cantiidadDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 cantiidadDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 cantiidadDato.setBorder(0);
						
						 PdfPCell medidaDato=new PdfPCell(new Phrase(detalle.getProducto(), fuenteDescripcion));
						 medidaDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 medidaDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 medidaDato.setBorder(0);
						 
						 PdfPCell precioDato=new PdfPCell(new Phrase(String.valueOf(detalle.getPrecioVenta()), fuenteDescripcion));
						 precioDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 precioDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 precioDato.setBorder(0);
						 
						 PdfPCell itbisDato=new PdfPCell(new Phrase(String.valueOf(detalle.getCodProducto()), fuenteDescripcion));
						 itbisDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 itbisDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 itbisDato.setBorder(0);
						 
						 PdfPCell totalDato=new PdfPCell(new Phrase(String.valueOf(detalle.getV_total()), fuenteDescripcion));
						 totalDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 totalDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 totalDato.setBorder(0);
						 
						 ventas.addCell(cantiidadDato);
						 ventas.addCell(medidaDato);
						 ventas.addCell(itbisDato);
						 ventas.addCell(precioDato);
						 ventas.addCell(totalDato);
						 
				 }	 
			
			 
			 
			 //DATOS PIE  DE PAGINA
			 PdfPTable footer=new PdfPTable(2);
			 footer.setWidthPercentage(100);
			 float[] medidaCeldaFooter= {15f,20f};
			 footer.setWidths(medidaCeldaFooter);
			 
			
						
						 PdfPCell observacion=new PdfPCell(new Phrase("Observacion", fuenteTitulo));
						 observacion.setVerticalAlignment(Element.ALIGN_CENTER);
						 observacion.setHorizontalAlignment(Element.ALIGN_RIGHT);
						 observacion.setBorder(0);
							PdfPCell observacionDato=new PdfPCell(new Phrase(String.valueOf(factura.getObervacion()), fuenteDescripcion));
							observacionDato.setVerticalAlignment(Element.ALIGN_CENTER);
							observacionDato.setHorizontalAlignment(Element.ALIGN_LEFT);
							observacionDato.setBorder(0);
						
						PdfPCell subTotal=new PdfPCell(new Phrase("SubTotal", fuenteTitulo));
						subTotal.setVerticalAlignment(Element.ALIGN_CENTER);
						subTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
						subTotal.setBorder(0);
						PdfPCell subTotalDato=new PdfPCell(new Phrase(String.valueOf(factura.getSubtotal()), fuenteDescripcion));
						subTotalDato.setVerticalAlignment(Element.ALIGN_CENTER);
						subTotalDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						subTotalDato.setBorder(0);
						
						
						PdfPCell totalVentas=new PdfPCell(new Phrase("Total", fuenteTitulo));
						totalVentas.setVerticalAlignment(Element.ALIGN_CENTER);
						totalVentas.setHorizontalAlignment(Element.ALIGN_RIGHT);
						totalVentas.setBorder(0);
						PdfPCell totalVentasDato=new PdfPCell(new Phrase(String.valueOf(factura.getTotal()), fuenteDescripcion));
						totalVentasDato.setVerticalAlignment(Element.ALIGN_CENTER);
						totalVentasDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						totalVentasDato.setBorder(0);
						
						
						
						 footer.addCell(observacion);
						 footer.addCell(observacionDato);
						 footer.addCell(subTotal);
						 footer.addCell(subTotalDato);
						 footer.addCell(totalVentas);
						 footer.addCell(totalVentasDato);
						 
						
							
					
			
			 document.add(saltoLinea);
			 document.add(ventas);
			 document.add(footer);
		 document.close();
		 
		 
		 File abrirArchivo=new File("C:/Users/HP/Desktop/ticket.pdf");
		 Desktop.getDesktop().open(abrirArchivo);
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
}

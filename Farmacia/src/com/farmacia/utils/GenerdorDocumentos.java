package com.farmacia.utils;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;


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
	private ControlFormatos formato=new ControlFormatos();
	
	public void generarPDFs(String codigo, int cantidad,String carpetaSeleccionada)  {
		
		try {
			
			LocalDateTime hora = LocalDateTime.now();
	        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	     
			String h=carpetaSeleccionada.replaceAll( Matcher.quoteReplacement(File.separator), "/")+"/codigo"+hora.format(f)+".pdf";
			
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
		
		LocalDateTime hora = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); 
        ///hora.format(f)
        
		Factura factura=new FacturaDao().searchFacturaId(idFactura);
		List<Detalle>listDetalles=new DetalleDao().listarDetallesId(idFactura);
		
		
		Rectangle r=new Rectangle(210,400);
		
		Document document=new Document(r,9f,9f,7f,7f);
		String archivoFactura="temp/ticket"+hora.format(f)+".pdf";
	try {
		FileOutputStream archivo=new FileOutputStream(archivoFactura);
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
		
		PdfPCell empresa=new PdfPCell(new Phrase("MAS QUE FARMACIA 11", fuenteTitulo));
		empresa.setVerticalAlignment(Element.ALIGN_CENTER);
		empresa.setHorizontalAlignment(Element.ALIGN_CENTER);
		empresa.setBorder(0);
		PdfPCell direccion=new PdfPCell(new Phrase("Quito-Ecuador", fuenteTitulo));
		 direccion.setVerticalAlignment(Element.ALIGN_CENTER);
		 direccion.setHorizontalAlignment(Element.ALIGN_CENTER);
		 direccion.setBorder(0);
		 PdfPCell rnc= new PdfPCell(new Phrase("RUC: 1724392582001", fuenteTitulo));
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
			
			try {
				String query="";
				/*int contador=0;
				Statement st= con.createStatement();
				ResultSet rs=st.executeQuery(query);
				while(rs.next()) {
					contador++;
					if(contador<=1) {*/
				System.out.println(factura.getN_cliente());
						PdfPCell nombreCliente=new PdfPCell(new Phrase("Cliente", fuenteTitulo));
						nombreCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						nombreCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						nombreCliente.setBorder(0);
						PdfPCell datoCliente=new PdfPCell(new Phrase(factura.getN_cliente(), fuenteDescripcion));
						datoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						datoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						datoCliente.setBorder(0);
						 encabezadoCliente.addCell(nombreCliente);
						 encabezadoCliente.addCell(datoCliente);
						 
						 
						 PdfPCell documentoCliente=new PdfPCell(new Phrase("RUC", fuenteTitulo));
						 documentoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						 documentoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						 documentoCliente.setBorder(0);
						PdfPCell datoDocumentoCliente=new PdfPCell(new Phrase(factura.getRuc(), fuenteDescripcion));
						datoDocumentoCliente.setVerticalAlignment(Element.ALIGN_CENTER);
						datoDocumentoCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
						datoDocumentoCliente.setBorder(0);
					    encabezadoCliente.addCell(documentoCliente);
					    encabezadoCliente.addCell(datoDocumentoCliente);
							 
							 
							 PdfPCell fechaCompra=new PdfPCell(new Phrase("Fecha", fuenteTitulo));
							 fechaCompra.setVerticalAlignment(Element.ALIGN_CENTER);
							 fechaCompra.setHorizontalAlignment(Element.ALIGN_LEFT);
							 fechaCompra.setBorder(0);
						     PdfPCell datofechaCompra=new PdfPCell(new Phrase(factura.getFecha().toString().replaceFirst("T", " "), fuenteDescripcion));
						     datofechaCompra.setVerticalAlignment(Element.ALIGN_CENTER);
						     datofechaCompra.setHorizontalAlignment(Element.ALIGN_LEFT);
						     datofechaCompra.setBorder(0);
						     encabezadoCliente.addCell(fechaCompra);
						     encabezadoCliente.addCell(datofechaCompra); 
								 
								 
								 PdfPCell vendedor=new PdfPCell(new Phrase("Venndedor", fuenteTitulo));
								 vendedor.setVerticalAlignment(Element.ALIGN_CENTER);
								 vendedor.setHorizontalAlignment(Element.ALIGN_LEFT);
								 vendedor.setBorder(0);
								 PdfPCell datoVendedor=new PdfPCell(new Phrase(factura.getUsuario(), fuenteDescripcion));
								 datoVendedor.setVerticalAlignment(Element.ALIGN_CENTER);
								 datoVendedor.setHorizontalAlignment(Element.ALIGN_LEFT);
								 datoVendedor.setBorder(0);
								 encabezadoCliente.addCell(vendedor);
								 encabezadoCliente.addCell(datoVendedor); 		 
								 
								 PdfPCell nFactura=new PdfPCell(new Phrase("Factura N° ", fuenteTitulo));
								 nFactura.setVerticalAlignment(Element.ALIGN_CENTER);
								 nFactura.setHorizontalAlignment(Element.ALIGN_LEFT);
								 nFactura.setBorder(0);
								 PdfPCell datonFactura=new PdfPCell(new Phrase(formato.darFormatoNumeroCeros(factura.getCodFactura()), fuenteDescripcion));
								 datonFactura.setVerticalAlignment(Element.ALIGN_CENTER);
								 datonFactura.setHorizontalAlignment(Element.ALIGN_LEFT);
								 datonFactura.setBorder(0);
								 encabezadoCliente.addCell(nFactura);
								 encabezadoCliente.addCell(datonFactura); 
					//}
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.add(encabezadoCliente);
			
			//dato del detalle d la venta
			 PdfPTable ventas=new PdfPTable(4);
			 ventas.setWidthPercentage(100);
			 float[] medidaCeldaVentas= {15f,30f,20f,15f};
			 ventas.setWidths(medidaCeldaVentas);
			
			 
			 PdfPCell cantidad=new PdfPCell(new Phrase("Cant.", fuenteTitulo));
			 cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
			 cantidad.setHorizontalAlignment(Element.ALIGN_LEFT);
			 cantidad.setBorder(0);
			
			 PdfPCell medida=new PdfPCell(new Phrase("Producto", fuenteTitulo));
			 medida.setVerticalAlignment(Element.ALIGN_CENTER);
			 medida.setHorizontalAlignment(Element.ALIGN_LEFT);
			 medida.setBorder(0);
			 
			 PdfPCell precio=new PdfPCell(new Phrase("Precio. U", fuenteTitulo));
			 precio.setVerticalAlignment(Element.ALIGN_CENTER);
			 precio.setHorizontalAlignment(Element.ALIGN_LEFT);
			 precio.setBorder(0);
			 
			 
			 
			 PdfPCell total=new PdfPCell(new Phrase("Total", fuenteTitulo));
			 total.setVerticalAlignment(Element.ALIGN_CENTER);
			 total.setHorizontalAlignment(Element.ALIGN_LEFT);
			 total.setBorder(0);
			 
			 ventas.addCell(cantidad);
			 ventas.addCell(medida);
			
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
						 
						
						 
						 PdfPCell totalDato=new PdfPCell(new Phrase(String.valueOf(detalle.getV_total()), fuenteDescripcion));
						 totalDato.setVerticalAlignment(Element.ALIGN_CENTER);
						 totalDato.setHorizontalAlignment(Element.ALIGN_LEFT);
						 totalDato.setBorder(0);
						 
						 ventas.addCell(cantiidadDato);
						 ventas.addCell(medidaDato);
						 
						 ventas.addCell(precioDato);
						 ventas.addCell(totalDato);
						 
				 }	 
					//}
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			 
			 //DATOS PIE  DE PAGINA
			 PdfPTable footer=new PdfPTable(2);
			 footer.setWidthPercentage(100);
			 float[] medidaCeldaFooter= {15f,20f};
			 footer.setWidths(medidaCeldaFooter);
			 
			 try {
				 String query="";
				
						
						 PdfPCell observacion=new PdfPCell(new Phrase("Observacion", fuenteTitulo));
						 observacion.setVerticalAlignment(Element.ALIGN_CENTER);
						 observacion.setHorizontalAlignment(Element.ALIGN_RIGHT);
						 observacion.setBorder(0);
							PdfPCell observacionDato=new PdfPCell(new Phrase(String.valueOf(factura.getObervacion()), fuenteDescripcion));
							observacionDato.setVerticalAlignment(Element.ALIGN_CENTER);
							observacionDato.setHorizontalAlignment(Element.ALIGN_RIGHT);
							observacionDato.setBorder(0);
						
						PdfPCell subTotal=new PdfPCell(new Phrase("SubTotal", fuenteTitulo));
						subTotal.setVerticalAlignment(Element.ALIGN_CENTER);
						subTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
						subTotal.setBorder(0);
						PdfPCell subTotalDato=new PdfPCell(new Phrase(String.valueOf(factura.getSubtotal()), fuenteDescripcion));
						subTotalDato.setVerticalAlignment(Element.ALIGN_CENTER);
						subTotalDato.setHorizontalAlignment(Element.ALIGN_RIGHT);
						subTotalDato.setBorder(0);
						
						
						PdfPCell totalVentas=new PdfPCell(new Phrase("Total", fuenteTitulo));
						totalVentas.setVerticalAlignment(Element.ALIGN_CENTER);
						totalVentas.setHorizontalAlignment(Element.ALIGN_RIGHT);
						totalVentas.setBorder(0);
						PdfPCell totalVentasDato=new PdfPCell(new Phrase(String.valueOf(factura.getTotal()), fuenteDescripcion));
						totalVentasDato.setVerticalAlignment(Element.ALIGN_CENTER);
						totalVentasDato.setHorizontalAlignment(Element.ALIGN_RIGHT);
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
				e.printStackTrace();
			}
			
			 document.add(saltoLinea);
			 document.add(ventas);
			 document.add(saltoLinea);
			 document.add(footer);
		 document.close();
		 
		 
		 File abrirArchivo=new File(archivoFactura);
		//Desktop.getDesktop().open(abrirArchivo);
		 try {
			 imprimir(abrirArchivo);
			
		} catch (PrinterException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de impresion", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
	


	
	public void generarReporteVentas(String dest,String administrador,int codUsuario,String fechaInicio,String fechaFin)  {
	    Document document = new Document();
		LocalDateTime hora = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
     
		String h=dest.replaceAll( Matcher.quoteReplacement(File.separator), "/")+"/reporteVenta"+hora.format(f)+".pdf";
		
	    try {
	    	PdfWriter.getInstance(document, new FileOutputStream(h));
	    document.open();
	    PdfPTable table = new PdfPTable(6);
	    table.setWidths(new int[]{1, 2, 1, 1, 1,1});
	    table.addCell(createCell("Cod", 2, 1, Element.ALIGN_LEFT));
	    table.addCell(createCell("Fecha", 2, 1, Element.ALIGN_LEFT));
	    table.addCell(createCell("Cliente", 2, 1, Element.ALIGN_LEFT));
	    table.addCell(createCell("Vendedor", 2, 1, Element.ALIGN_LEFT));
	    table.addCell(createCell("SubTotal", 2, 1, Element.ALIGN_LEFT));
	    table.addCell(createCell("Total", 2, 1, Element.ALIGN_LEFT));
	   
	    double total=0;
	    
	    List<Factura> facturas=new FacturaDao().listarFacturasReporte();
	    for (Factura factura : facturas) {
	        table.addCell(createCell(String.valueOf( factura.getCodFactura()), 1, 1, Element.ALIGN_LEFT));
	        table.addCell(createCell(factura.getFecha().toString().replaceAll("T", " "), 1, 1, Element.ALIGN_LEFT));
	        table.addCell(createCell(factura.getN_cliente(), 1, 1, Element.ALIGN_RIGHT));
	        table.addCell(createCell(factura.getUsuario(), 1, 1, Element.ALIGN_RIGHT));
	        table.addCell(createCell(String.valueOf(factura.getSubtotal()), 1, 1, Element.ALIGN_RIGHT));
	        double totalC=factura.getTotal();
	        table.addCell(createCell(String.valueOf(totalC), 1, 1, Element.ALIGN_RIGHT));
	        total+=totalC;
	    }
	    table.addCell(createCell("Total", 2, 4, Element.ALIGN_LEFT));
	    table.addCell(createCell(new ControlFormatos().redondearDosDecimales(total), 2, 2, Element.ALIGN_RIGHT));
	    document.add(table);
	    document.close();
	    total=0;
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
	
	
	
	
	public PdfPCell createCell(String content, float borderWidth, int colspan, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(content));
	    cell.setBorderWidth(borderWidth);
	    cell.setColspan(colspan);
	    cell.setHorizontalAlignment(alignment);
	    return cell;
	}
	
public void imprimir(File archivo) throws PrinterException, IOException {
    // Indicamos el nombre del archivo Pdf que deseamos imprimir
    PDDocument document = Loader.loadPDF(archivo);

    PrinterJob job = PrinterJob.getPrinterJob();
    
   // LOGGER.log(Level.INFO, "Mostrando el dialogo de impresion");
    if (job.printDialog() == true) {    
    	
        job.setPageable(new PDFPageable(document));
       
       // LOGGER.log(Level.INFO, "Imprimiendo documento");
        job.print();
        
    }
    
    	archivo.delete();
    
    
    
}





}

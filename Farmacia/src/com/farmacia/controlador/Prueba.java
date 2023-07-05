package com.farmacia.controlador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.farmacia.utils.ControlFormatos;
import com.farmacia.utils.GenerdorDocumentos;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class Prueba {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		GenerdorDocumentos generador=new GenerdorDocumentos();
		String valor="2.5";
		System.out.println(new ControlFormatos().validarSiNumeroDecimal(valor));
		
			//generador.generarTicket(1);
			//new Prueba().imprimir();
		
	}
	
	

}

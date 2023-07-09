package com.farmacia.controlador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.farmacia.utils.ControlFormatos;
import com.farmacia.utils.GenerdorDocumentos;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.farmacia.bd.*;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Date;

public class Prueba {
	
	
	public String getPropValues()  {
	
		String propFileName = "C:/Users/HP/git/FarmaciaVentas/Farmacia/src/com/farmacia/utils/.env";
		InputStream inputStream;
	    String result=null;
			try {
				Properties prop = new Properties();
				
				 inputStream = Prueba.class.getResourceAsStream(propFileName);
				
	 
				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
	 
				Date time = new Date(System.currentTimeMillis());
	 
				// get the property value and print it out
				String prop1 = prop.getProperty("prop1");
				String prop2 = prop.getProperty("prop2");
				String prop3 = prop.getProperty("prop3");
	 
	      //after values are loaded you can do anything with them
	      //here I will set them as System properties
				System.setProperty("prop1",prop1);
	      System.setProperty("prop1",prop2);
	      System.setProperty("prop1",prop3);
	  	 prop1 = prop.getProperty("prop1");
		 prop2 = prop.getProperty("prop2");
		 prop3 = prop.getProperty("prop3");
	       result=prop1+prop2;
	      System.out.println(result + "\n Properties Successfully Loaded On " + time);
	      inputStream.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			} finally {
				
			}
			return result;
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		//GenerdorDocumentos generador=new GenerdorDocumentos();
		String valor="2.5";
		//System.out.println(new ControlFormatos().validarSiNumeroDecimal(valor));
		
			//generador.generarTicket(1);
		//ConexionBD.obtenerNuevasCredenciales("farmacia", "localhost", "root", "root", "3306");
		try (InputStream input =new FileInputStream("src/com/farmacia/utils/.env")) {

            Properties prop = new Properties();

            
           /* prop.setProperty("db.localhost", "localhost");
            prop.setProperty("db.database", "farmacia");
            prop.setProperty("db.user", "root");
            prop.setProperty("db.password", "");
            prop.setProperty("db.port", "3306");*/

           
           prop.load( input);

            System.out.println(prop.getProperty("db.localhost")+
            		" usuario"+prop.getProperty("db.user"));

        } catch (IOException io) {
            io.printStackTrace();
        }
 

		
		
	}
	
	

}

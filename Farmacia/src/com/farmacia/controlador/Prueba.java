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



import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Date;

public class Prueba {
	
	
	
	
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

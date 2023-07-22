package com.farmacia.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.io.File;


public class Propiedades {
	
	
	private  String ruta=System.getProperty("java.class.path").replaceFirst("farmacia.exe", ".env");
  public Properties cargarArchivoProperties() {
	  createArchivo();
	  Properties propiedades=new Properties();
	  InputStream input;
	try {
		input = new FileInputStream( ruta);
		//input=getClass().getResourceAsStream("/app.properties");
		propiedades.load( input);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return propiedades;
	  
  }
  public void escribirArchivoProperties(String baseDatos,String host,String usuario,String password,String port) {
	  createArchivo();
	  Properties propiedades=new Properties();
	  OutputStream output;
	try {
		
		
		// output = new FileOutputStream("./resources/app.properties");
		output = new FileOutputStream(ruta);
		
		 propiedades.setProperty("db.localhost", host);
			
		 propiedades.setProperty("db.database", baseDatos);
		 propiedades.setProperty("db.user", usuario);
		 propiedades.setProperty("db.password", password);
		 propiedades.setProperty("db.port", port);
		 propiedades.store(output, null);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
  }
  
  private void createArchivo() {
	  try {
             /*String ruta=getClass()
                     .getProtectionDomain()
                     .getCodeSource()
                     .getLocation()
                     .toURI()
                     .getPath();*/
		 
	        File New_File = new File(ruta);

	        if (New_File.createNewFile()){
	            System.out.println("The file is created successfully!");
	        }
	        else{
	            System.out.println("The file already exists.");
	        }

	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }

  }
  
}

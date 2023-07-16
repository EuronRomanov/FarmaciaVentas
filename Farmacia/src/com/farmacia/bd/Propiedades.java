package com.farmacia.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Propiedades {
  public Properties cargarArchivoProperties() {
	  Properties propiedades=new Properties();
	  InputStream input;
	try {
		input = new FileInputStream("src/com/farmacia/utils/app.properties");
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
	  Properties propiedades=new Properties();
	  OutputStream output;
	try {
		 output = new FileOutputStream("src/com/farmacia/utils/app.properties");
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
}

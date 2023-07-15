package com.farmacia.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Bodega;
import com.farmacia.entidades.Factura;

public class BodegaDao {
	private static Connection con=ConexionBD.conectar();
	
	
	public List listarDatosBodega(int codProducto,String fechaInicio,String fechaFin) {
		 List<Bodega> ListaCl = new ArrayList();
	       String sql = "SELECT cantidadIngresada,fechaIngreso,fechaCaducidad, "
	       		+ "IF(fechaCuandoCaduca<=DATE(NOW()), 'YES', 'NO') AS Caduca FROM bodega WHERE codProducto=?";
	      
	       try {
	    	   DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	    	   DateTimeFormatter parser2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	  
	    	  
	                sql+=" AND DATE(fechaIngreso) BETWEEN ? AND ?";
	               
	                //Date dateInicio = formatter.parse(fechaInicio);
	                PreparedStatement ps = con.prepareStatement(sql);
	                    ps.setInt(1,codProducto);
			    	   ps.setString(2,fechaInicio);
			    	   ps.setString(3,fechaFin);
			
	    	  
	    	   
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	              
	               
	               Bodega cl=new Bodega( rs.getInt("cantidadIngresada"),
	            		   LocalDateTime.parse(rs.getTimestamp("fechaIngreso").toString(),parser), 
	            		   LocalDate.parse(rs.getDate("fechaCaducidad").toString(),parser2),
	            		   rs.getString(4));
	               
	               
	               ListaCl.add(cl);
	           }
	       } catch (SQLException e) {
	           System.out.println(e.toString());
	       }
	       return ListaCl;
	}
}

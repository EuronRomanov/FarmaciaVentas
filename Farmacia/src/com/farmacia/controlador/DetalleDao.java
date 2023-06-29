package com.farmacia.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Factura;

public class DetalleDao {
	private static Connection con=ConexionBD.conectar();
	
	public List listarDetallesId(int key){
	       List<Detalle> ListaCl = new ArrayList();
	       String sql = "SELECT codCarrito,Detalle.cantidad as cantidadD,nombreProducto,v_total,precioVenta FROM Detalle,Producto"
	       		+ " WHERE Detalle.codProducto=Producto.codProducto"
	       		+ " AND codFactura=?";
	       try {
	    	  
	    	   PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1,  key );
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	              
	               
	        	   Detalle cl=new Detalle(rs.getInt("codCarrito"), 
	        			   rs.getInt("cantidadD"),
	        			   rs.getString("nombreProducto"),
	        			   rs.getDouble("v_total"),rs.getDouble("precioVenta"));
	               
	               
	               ListaCl.add(cl);
	           }
	       } catch (SQLException e) {
	           System.out.println(e.toString());
	       }
	       return ListaCl;
	   }
	 
}

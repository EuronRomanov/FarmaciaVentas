package com.farmacia.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Factura;
import com.farmacia.form.Main;
import com.farmacia.form.RenderTabla;

public class DetalleDao {
	private static Connection con=ConexionBD.conectar();
	private DefaultTableModel modelo = new DefaultTableModel();
	
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
	
	
	  public void listarDetalleTable(int key,JTable tblDetalles) {
	        List<Detalle> ListarCl = this.listarDetallesId(key);
	        modelo = (DefaultTableModel) tblDetalles.getModel();
	        modelo.setRowCount(0);
	       
	        
	        Object[] ob = new Object[5];
	        for (int i = 0; i < ListarCl.size(); i++) {
	            ob[0] = ListarCl.get(i).getCodCarrito();
	            ob[1] = ListarCl.get(i).getProducto();
	            ob[2] = ListarCl.get(i).getCantidad();
	            ob[3] = ListarCl.get(i).getPrecioVenta();
	            ob[4] = ListarCl.get(i).getV_total();
	          
	            modelo.addRow(ob);
	        }
	       
	        tblDetalles.setModel(modelo);
	        

	    }
	    
	  public Detalle searchDetalleId(int key){
		  Detalle cl=null;
	       String sql = "SELECT * FROM Detalle WHERE  codCarrito=?";
	       try {
	    	  
	    	   PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1,  key );
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	              
	               
	        	    cl=new Detalle(rs.getInt("codCarrito"), 
	        			   rs.getInt("cantidad"),
	        			   rs.getInt("codProducto"),
	        			   rs.getDouble("v_total"),rs.getInt("codFactura"));
	               
	        	    
	               
	           }
	       } catch (SQLException e) {
	           System.out.println(e.toString());
	       }
	       return cl;
	   }
	
	 
}

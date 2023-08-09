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
import com.farmacia.bd.DBUtil;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Factura;
import com.farmacia.form.Main;
import com.farmacia.form.RenderTabla;

public class DetalleDao {
	//private static Connection con=ConexionBD.conectar();
	private  Connection con=new DBUtil().getConexion();
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public List listarDetallesId(int key){
	       List<Detalle> ListaCl = new ArrayList();
	       String sql = "SELECT codCarrito,cantidadD,nombreProducto,v_total,precioVenta FROM view_listarDetallesId WHERE codFactura=?";
	       try {
	    	  
	    	   PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1,  key );
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	              
	               
	        	   Detalle cl=new Detalle(rs.getInt(1), 
	        			   rs.getInt(2),
	        			   rs.getString(3),
	        			   rs.getDouble(4),rs.getDouble(5));
	               
	               
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
	       String sql = "SELECT * FROM view_searchDetalleId WHERE codCarrito=? ";
	       try {
	    	  
	    	   PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1,  key );
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	              
	               
	        	    cl=new Detalle(rs.getInt(1), 
	        			   rs.getInt(2),
	        			   rs.getInt(3),
	        			   rs.getDouble(4),rs.getInt(5));
	               
	        	    
	               
	           }
	       } catch (SQLException e) {
	           System.out.println(e.toString());
	       }
	       return cl;
	   }
	
	 ///elimnar detalle
	    public void deleteProducto(int id){
	        String sql = "DELETE FROM Detalle WHERE codCarrito=?";
	        try{
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, id);

	            ps.executeUpdate();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	           /* try {
	                con.close();
	            } catch (SQLException e) {
	                System.out.println(e.toString());
	            }*/
	        }
	    }
	    
	    
	    
	    public Detalle existeDetalle(String key,JTable tblDetalles) {
	    	Detalle detalle=null;
	    	//int valor=Integer.parseInt(key);
	    	for (int i = 0; i < tblDetalles.getRowCount(); i++) {
	    	String j=tblDetalles.getValueAt(i, 1).toString();
	    	if (key.equals(j)) {
	    	
	    	int codCarrito=	Integer.parseInt(tblDetalles.getValueAt(i, 0).toString());
	    	int cantidad=Integer.parseInt(tblDetalles.getValueAt(i, 2).toString());
	    	double valor=Double.parseDouble(tblDetalles.getValueAt(i, 4).toString());
	    	
	    	detalle=new Detalle();
	    	detalle.setCodCarrito(codCarrito);
	    	detalle.setCantidad(cantidad);
	    	detalle.setV_total(valor);
	    	break;
			}
	    	}
	    	return detalle;
	    }
}

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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.farmacia.bd.ConexionBD;
import com.farmacia.bd.DBUtil;
import com.farmacia.entidades.Bodega;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Factura;
import com.farmacia.entidades.Producto;

public class BodegaDao {
	private static Connection con=new DBUtil().getConexion();
	private DefaultTableModel modelo = new DefaultTableModel();
	
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
	
	
	public void searchProducto(int key,JTable tblProducto){
        
        String sql = "SELECT * FROM view_cantidadBodega WHERE codProducto =? ";
        modelo = (DefaultTableModel) tblProducto.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[6];
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,  key );
          
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	ob[0]=rs.getInt(1);
            	ob[1]=rs.getString(2); 
            	ob[2]=rs.getInt(3); 
            	ob[3]=rs.getTimestamp(4).toString().replaceFirst("T", " ");
            	ob[4]=rs.getDate(5).toString(); 
            	ob[5]=rs.getDate(6).toString();
            	
            	 modelo.addRow(ob);
            }
            tblProducto.setModel(modelo);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
        }    
       
    }


	public void agregarBodega(int cantidad, LocalDate fechaCaduca, int codProducto) {
		// TODO Auto-generated method stub
		
	}


	public Bodega searchBodegaId(int key) {
          Bodega bodega=null;
        String sql = "SELECT * FROM view_bodegaSearchId WHERE  codBodega=?";
        
        Object[] ob = new Object[6];
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,  key );
          
            ResultSet rs = ps.executeQuery();
            DateTimeFormatter parser2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while(rs.next()){
            	
            	
            	 bodega=new Bodega(rs.getInt(1), rs.getInt(2),  LocalDate.parse(rs.getDate(3).toString(),parser2), rs.getString(4));
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
        }    
		
		
		
		return  bodega;
	}



	 
	
	
}

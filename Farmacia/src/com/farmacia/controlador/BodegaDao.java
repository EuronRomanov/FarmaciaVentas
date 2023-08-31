package com.farmacia.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.farmacia.bd.ConexionBD;
import com.farmacia.bd.DBUtil;
import com.farmacia.entidades.Bodega;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Factura;
import com.farmacia.entidades.Producto;
import com.farmacia.form.Main;
import com.farmacia.form.RenderTabla;
import com.farmacia.utils.ControlFormatos;

public class BodegaDao {
	private static Connection con=new DBUtil().getConexion();
	private DefaultTableModel modelo = new DefaultTableModel();
	 private ControlFormatos controlFormato=new ControlFormatos();
	
	public List<Bodega> listarDatosBodega(int codProducto,String fechaInicio,String fechaFin) {
		 List<Bodega> ListaCl = new ArrayList<Bodega>();
	       String sql = "SELECT cantidadIngresada,fechaIngreso,fechaCaducidad, "
	       		+ "IF(fechaCuandoCaduca<=DATE(NOW()), 'YES', 'NO') AS Caduca FROM bodega WHERE codProducto=? ";
	      
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


	 public void agregarBodega(Bodega bodega)
	   {
		 
		
	       int resultado;
	       try {            
	            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
	            CallableStatement proc = con.prepareCall(" CALL proc_agregarBodega(?,?,?,?) ");
	            //se cargan los parametros de entrada
	            proc.setInt("cantidadIngresadaIn", bodega.getCantidadIngresada());//Tipo String
	           
	            proc.setDate("fechaCaducidadIn",java.sql.Date.valueOf(bodega.getFechaCaducidad()) );//Tipo entero
	            proc.setInt("codProductoIn",bodega.getCodProducto());//Tipo entero
	            
	            // parametros de salida
	            proc.registerOutParameter("respuesta", Types.CHAR);//Tipo String
	            // Se ejecuta el procedimiento almacenado
	            proc.execute();            
	            // devuelve el valor del parametro de salida del procedimiento
	            resultado = proc.getInt("respuesta");
	            if (resultado =='0') {
	            	JOptionPane.showMessageDialog(null, "Revise si  este producto existe");
				}
	        } 
	       catch (Exception e) {                  
	            System.out.println(e);
	       }
	       //return resultado;
	   }
	 
	 public void eliminarBodega(int codBodega)
	   {
		 
		
	       int resultado;
	       try {            
	            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
	            CallableStatement proc = con.prepareCall(" CALL proc_eliminarBodega(?,?) ");
	            //se cargan los parametros de entrada
	            proc.setInt("codBodegaIn", codBodega);//Tipo String
	           
	            
	            // parametros de salida
	            proc.registerOutParameter("respuesta", Types.CHAR);//Tipo String
	            // Se ejecuta el procedimiento almacenado
	            proc.execute();            
	            // devuelve el valor del parametro de salida del procedimiento
	            resultado = proc.getInt("respuesta");
	            if (resultado =='0') {
	            	JOptionPane.showMessageDialog(null, "Revise si  este producto existe");
				}
	        } 
	       catch (Exception e) {                  
	            System.out.println(e);
	       }
	       //return resultado;
	   }
	 
	 public void actualizarBodega(Bodega bodega)
	   {
		 
		
	       int resultado;
	       try {            
	            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
	            CallableStatement proc = con.prepareCall(" CALL proc_actualizarBodega(?,?,?,?) ");
	            //se cargan los parametros de entrada
	            proc.setInt("codBodegaIn",bodega.getCodBodega());//Tipo entero
	            proc.setInt("cantidadIngresadaIn", bodega.getCantidadIngresada());//Tipo String
	           
	            proc.setDate("fechaCaducidadIn",java.sql.Date.valueOf(bodega.getFechaCaducidad()) );//Tipo entero
	            
	            
	            // parametros de salida
	            proc.registerOutParameter("respuesta", Types.CHAR);//Tipo String
	            // Se ejecuta el procedimiento almacenado
	            proc.execute();            
	            // devuelve el valor del parametro de salida del procedimiento
	            resultado = proc.getInt("respuesta");
	            if (resultado =='0') {
	            	JOptionPane.showMessageDialog(null, "Revise si  este producto existe");
				}
	        } 
	       catch (Exception e) {                  
	            System.out.println(e);
	       }
	       //return resultado;
	   }
	
	 
	 public String consultaProductosCaducados() {
         String bodega="";
       String sql = "SELECT * FROM view_alertaProductoCaduca";
       
      
       try{
           PreparedStatement ps = con.prepareStatement(sql);
           
         
           ResultSet rs = ps.executeQuery();
         
           while(rs.next()){
           	
           	bodega+=rs.getInt(1);
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
	 
	 
	 public void getProductosCaducados(JTable tblProductoCaducados){
	        
	        String sql = "SELECT * FROM view_alertaProductosCaducos";
	        modelo = (DefaultTableModel) tblProductoCaducados.getModel();
	        modelo.setRowCount(0);
	        Object[] ob = new Object[9];
	        try{
	            PreparedStatement ps = con.prepareStatement(sql);
	           
	          
	            ResultSet rs = ps.executeQuery();
	            JButton button=new JButton("ELiminar");
	            button.setName("btnEliminarPBodega");
	            button.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/icon-delete.png")));
	            while(rs.next()){
	            	ob[0]=rs.getInt(1);
	            	ob[1]=rs.getDate(2); 
	            	ob[2]=rs.getDate(3); 
	            	ob[3]=rs.getInt(4);
	            	ob[4]=rs.getString(5); 
	            	ob[5]=rs.getString(6);
	            	ob[6]=rs.getDouble(7); 
	            	ob[7]=rs.getString(8);
	            	ob[8]=button;
	            	 modelo.addRow(ob);
	            }
	            tblProductoCaducados.setDefaultRenderer(Object.class,new RenderTabla());
	            
	            tblProductoCaducados.setModel(modelo);
	            tblProductoCaducados.setRowHeight(30);
	            
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


	public void retirarProductoBodega(int key) {
		// TODO Auto-generated method stub
		   int resultado;
	       try {            
	            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
	            CallableStatement proc = con.prepareCall(" CALL proc_eliminarProductoBodega(?,?) ");
	            //se cargan los parametros de entrada
	            proc.setInt("codBodegaIn",key);//Tipo entero
	           
	            
	            
	            // parametros de salida
	            proc.registerOutParameter("respuesta", Types.CHAR);//Tipo String
	            // Se ejecuta el procedimiento almacenado
	            proc.execute();            
	            // devuelve el valor del parametro de salida del procedimiento
	            resultado = proc.getInt("respuesta");
	            if (resultado =='0') {
	            	JOptionPane.showMessageDialog(null, "Revise si  este producto existe");
				}
	        } 
	       catch (Exception e) {                  
	            System.out.println(e);
	       }
	       //return resultado;
	}


	public void cargarProductosBodegasById(JComboBox<Bodega> cmbBodega, int codProducto) {
		 
		cmbBodega.removeAllItems();
	      	 
	      	 
	      	String sql = "SELECT * FROM view_productosBodegasById WHERE codProducto =? ";
	      	 try{
	      		DateTimeFormatter parser2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	             PreparedStatement ps = con.prepareStatement(sql);
	             ps.setInt(1,  codProducto );
	           
	             ResultSet rs = ps.executeQuery();

	             while(rs.next()){
	             	
	             	Bodega bodega=new Bodega(rs.getInt(1), rs.getInt(2),  LocalDate.parse(rs.getDate(3).toString(),parser2));
	             	cmbBodega.addItem(bodega);
	             }
	             
	         }catch(Exception e){
	             e.printStackTrace();
	         }
		
	}
}

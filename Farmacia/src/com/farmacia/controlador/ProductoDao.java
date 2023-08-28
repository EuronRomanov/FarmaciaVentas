package com.farmacia.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.text.DateFormatter;

import com.farmacia.bd.ConexionBD;
import com.farmacia.bd.DBUtil;
import com.farmacia.entidades.Categoria;
import com.farmacia.entidades.Producto;
import com.farmacia.form.Main;
import com.farmacia.form.RenderTabla;





public class ProductoDao {
	//private static Connection con=ConexionBD.conectar();
	private  Connection con=new DBUtil().getConexion();
	private DefaultTableModel modelo = new DefaultTableModel();
	
	
	
	
	
	/*
	 * 
	 * insetar
	 */
	 public boolean registrarProducto(Producto cl){
		 PreparedStatement stmtDetalle=null;
		 ResultSet rs = null;
	        String sql = "INSERT INTO Producto (nombreProducto,"
	        		+ "precioCompra,"
	        		+ "precioVenta,"
	        		+ "cantidad,"
	        		+ "unidadMedida,"
	        		+ "presentacion,"
	        		+ "marca,"
	        		+ "observaciones,"
	        		+ "formaFarmaceutica,"
	        		+ "codCategoria,"
	        		+ "codProveedor) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	        try {
	        	con.setAutoCommit(false);
	           // PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        	PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, cl.getNombreProducto());
	           
	           ps.setDouble(2, cl.getPrecioCompra());
	           ps.setDouble(3, cl.getPrecioVenta());
	           ps.setInt(4, cl.getCantidad());
	           ps.setString(5, cl.getUnidadMedida());
	           ps.setDouble(6, cl.getPresentacion());
	           ps.setString(7, cl.getMarca());
	           
	           ps.setString(8, cl.getObservaciones());
	           ps.setString(9, cl.getFormaFarmaceutica());
	           ps.setInt(10, cl.getCodCategoria());
	           ps.setInt(11, cl.getCodProveedor());
	            
	            ps.executeUpdate();
	            // get candidate id
	           /* rs = ps.getGeneratedKeys();
	            int productoId = 0;
	            if(rs.next())
	            	productoId = rs.getInt(1);
	            
	            
	            int width = 13;
	            stmtDetalle = con.prepareStatement("UPDATE Producto SET codigobarra=? WHERE codProducto=?");
	            stmtDetalle.setString(1,String.format("%0" + width + "d",productoId));
	            stmtDetalle.setInt(2,productoId);
            	stmtDetalle.executeUpdate();*/
	            
	            con.commit();
	            return true;
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e.toString());
	            if(con!=null)
				 {
				 System.out.println("Rollback");
				try {
				  //deshace todos los cambios realizados en los datos
				  con.rollback();
				 } catch (SQLException ex1) {
				      System.err.println( "No se pudo deshacer" + ex1.getMessage() );    
				 }
				}
	            return false;
	        }finally{
	            /*try {
	                con.close();
	            } catch (SQLException e) {
	                System.out.println(e.toString());
	            }*/
	        }
	    }
	
	
	 
	 /*
	  * listar
	  */
	 
	 public List<Producto> ListarProducto(){
	       List<Producto> ListaCl = new ArrayList<Producto>();
	       String sql = "select * from vista_productos";
	       try {
	           
	    	   PreparedStatement ps = con.prepareStatement(sql);
	    	   ResultSet rs = ps.executeQuery();
	    	   DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	    	   while (rs.next()) {      
	        	   
	               
	        	   Producto cl = new Producto(rs.getInt(1),
		                      rs.getString(2), 
		                      rs.getDouble(3),
		                      rs.getDouble(4),
		                      rs.getInt(5), 
		                      rs.getString(6),
		                      rs.getDouble(7), 
		                      rs.getString(8), 
		LocalDateTime.parse(rs.getTimestamp(9).toString(),parser),
		rs.getString(10),
		rs.getString(11),
		rs.getString(12), 
		 rs.getString(13));
	        	  
	               
	               ListaCl.add(cl);
	           }
	       } catch (SQLException e) {
	           System.out.println(e.toString());
	       }finally{
	            /*try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }*/
       } 
	       return ListaCl;
	   }
	 
	/**
     * search all clients in the tblClient whose name contains the @key
     * @param key
     * @return list of client whose name contains the @key
     */
    public void searchProducto(String key,JTable tblProducto){
       // ArrayList<Producto> result = new ArrayList<Producto>();
        String sql = "SELECT * FROM Producto,Categoria,Proveedor"
        		+ " WHERE Producto.codCategoria=Categoria.codCategoria "
        		+ "AND Producto.codProveedor=Proveedor.codProveedor "
        		+ "AND Producto.disposicion=1"
        		+ " AND Producto.codProducto LIKE ? OR Producto.nombreProducto LIKE ? OR Producto.formaFarmaceutica LIKE ?";
        modelo = (DefaultTableModel) tblProducto.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[14];
        JButton buttonProducto=new JButton("");
        buttonProducto.setName("btnProductoDetalle");
        buttonProducto.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/icon-lupa.png")));
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,  "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ps.setString(3, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	ob[0]=rs.getInt("codProducto");
            	ob[1]= rs.getString("nombreProducto");
            	ob[2]=rs.getDouble("precioCompra");
            	ob[3]=rs.getDouble("precioVenta");
            	ob[4]=rs.getInt("cantidad"); 
            	ob[5]=rs.getString("unidadMedida");
            	ob[6]=rs.getDouble("presentacion"); 
            	ob[7]=rs.getString("marca"); 
            	ob[8]=rs.getTimestamp("fechaRegistro").toString().replaceFirst("T", " ");
            	ob[9]=rs.getString("observaciones");
            	ob[10]=rs.getString("formaFarmaceutica");
            	ob[11]=rs.getInt("codCategoria");
            	ob[12]=rs.getInt("codProveedor");
            	 ob[13] =buttonProducto;
            	
               
            	 modelo.addRow(ob);
            }
            
            
            tblProducto.setDefaultRenderer(Object.class,new RenderTabla());
            
            tblProducto.setModel(modelo);
            tblProducto.setRowHeight(30);
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
    
    /**
     * update the @client
     * @param client
     */
    public void editProducto(Producto cl){
        String sql = "UPDATE Producto SET nombreProducto=?, "
        		+ "precioCompra=?,"
        		+ "precioVenta=?,"
        		+ "cantidad=?,"
        		+ "unidadMedida=?,"
        		+ "presentacion=?,"
        		+ "marca=?,"
        		+ "observaciones=?,"
        		+ "codCategoria=?,"
        		+ "codProveedor=?,"
        		+ "formaFarmaceutica=? WHERE codProducto=?";
        try{
        
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombreProducto());
	           ps.setDouble(2, cl.getPrecioCompra());
	           ps.setDouble(3, cl.getPrecioVenta());
	           ps.setInt(4, cl.getCantidad());
	           ps.setString(5, cl.getUnidadMedida());
	           ps.setDouble(6, cl.getPresentacion());
	           ps.setString(7, cl.getMarca());
	           ps.setString(8, cl.getObservaciones());
	           ps.setInt(9, cl.getCodCategoria());
	           ps.setInt(10, cl.getCodProveedor());
	           ps.setString(11, cl.getFormaFarmaceutica());
            
            ps.setInt(12, cl.getCodProducto());

            ps.executeUpdate();
        }catch(Exception e){
        	
           // e.printStackTrace();
        }finally{
            /*try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
        }
    }
    
    /**
     * delete the client whose id is @id
     * @param id
     */
    public void deleteProducto(int id){
        String sql = "UPDATE producto SET disposicion=0 WHERE codProducto=?";
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
    
    
    public void ListarProductoTable(JTable tblProducto) {
        List<Producto> ListarCl = this.ListarProducto();
        modelo = (DefaultTableModel) tblProducto.getModel();
        modelo.setRowCount(0);
        JButton buttonProducto=new JButton("");
        buttonProducto.setName("btnProductoDetalle");
        buttonProducto.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/icon-lupa.png")));
        Object[] ob = new Object[14];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getCodProducto();
            ob[1] = ListarCl.get(i).getNombreProducto();
           
            ob[2] = ListarCl.get(i).getPrecioCompra();
            ob[3] = ListarCl.get(i).getPrecioVenta();
            ob[4] = ListarCl.get(i).getCantidad();
            ob[5] = ListarCl.get(i).getUnidadMedida();
            ob[6] = ListarCl.get(i).getPresentacion();
            ob[7] = ListarCl.get(i).getMarca();
            ob[8] = ListarCl.get(i).getFechaRegistro().toString().replaceFirst("T", " ");
            ob[9] = ListarCl.get(i).getObservaciones();
            ob[10] = ListarCl.get(i).getFormaFarmaceutica();
            ob[11] = ListarCl.get(i).getCategoria();
            ob[12] =ListarCl.get(i).getProveedor();
            ob[13] =buttonProducto;
            modelo.addRow(ob);
        }
        tblProducto.setDefaultRenderer(Object.class,new RenderTabla());
        
        tblProducto.setModel(modelo);
        tblProducto.setRowHeight(30);
        

    }



	public Producto searchProductoId(int key ) {
		 
	        String sql = "SELECT * FROM view_searchProductoId WHERE  codProducto=?";
	        Producto producto =null;
	        try{
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, key );
	            ResultSet rs = ps.executeQuery();
	            DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	            while(rs.next()){
	            	 producto = new Producto(rs.getInt(1),
		                      rs.getString(2),
		                      rs.getDouble(3),
		                      rs.getDouble(4),
		                      rs.getInt(5), 
		                      rs.getString(6),
		                      rs.getDouble(7), 
		                      rs.getString(8), 
							LocalDateTime.parse(rs.getTimestamp(9).toString().replaceFirst("T", " "),parser ),
						    rs.getString(10),
						    rs.getString(11),
						    rs.getInt(12), 
						    rs.getInt(13));
	            	
	            	
	               
	               
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
		return producto;
	}
    
	
	
	public int searchCanitdadProductoId(int key ) {
		 
        String sql = "SELECT  cantidadIngresada FROM view_cantidadproductoid WHERE codBodega=?";
       
        int cantidad=0;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, key );
            ResultSet rs = ps.executeQuery();
           
            while(rs.next()){
            	cantidad= rs.getInt(1);
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
	return cantidad;
}
	
	 public void agregarProductoProCodigo(String key,JTable tblVentas){
		 String sql = "select * from view_productoPorCodigo WHERE  codBodega=?";
	        modelo = (DefaultTableModel) tblVentas.getModel();
	       
	        Object[] ob = new Object[5];
	        
	        
		 if (!this.existeProducto(key, tblVentas)) {
			  try{
		            PreparedStatement ps = con.prepareStatement(sql);
		            ps.setInt(1,  Integer.parseInt(key));
		            
		            ResultSet rs = ps.executeQuery();
		            JButton button=new JButton("ELiminar");
		            button.setName("btnEliminarVenta");
		            button.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/icon-delete.png")));
		            while(rs.next()){
		            	ob[0]=rs.getInt(1);
		            	ob[1]= rs.getString(2);
		            	 
		            	
		            	ob[2]=rs.getDouble(3);
		            	 
		            	ob[3]=1;
		            	
		            	ob[4]=button;
		            	
		            	
		               
		            	 modelo.addRow(ob);
		            	 
		            }
		            tblVentas.setDefaultRenderer(Object.class,new RenderTabla());
		           
		            tblVentas.setModel(modelo);
		            tblVentas.setRowHeight(30);
		        }catch(Exception e){
		            e.printStackTrace();
		        }  
		}		 
	       
	       
	       
	    }
    public void eliminarFilaJTable(int fila, JTable  tblVentas) {
    	DefaultTableModel dtm = (DefaultTableModel)  tblVentas.getModel(); //TableProducto es el nombre de mi tabla ;)
    	dtm.removeRow(fila); 
    	 tblVentas.setModel(dtm);
    }
    
    private boolean existeProducto(String key,JTable tblVentas) {
    	boolean flag=false;
    	int valor=Integer.parseInt(key);
    	for (int i = 0; i < tblVentas.getRowCount(); i++) {
    	int j=Integer.parseInt(tblVentas.getValueAt(i, 0).toString());
    	if (valor==j) {
    	int idc=searchCanitdadProductoId(valor);
    	int cantidad=Integer.parseInt(tblVentas.getValueAt(i, 3).toString())+1;
    	if (idc>=cantidad) {
    		tblVentas.setValueAt((cantidad), i, 3);
		} else {
			 JOptionPane.showMessageDialog(null, "stock actual es de "+idc);
		}
    	
    	flag=true;
    	break;
		}
    	}
    	return flag;
    }
    
    public void cargarListaProductos(JComboBox<Producto> cmbDetalleProductos) {
      	 List<Producto> ListarCl = this.ListarProducto();
      	cmbDetalleProductos.removeAllItems();
      	
      	 for (Producto proveedor : ListarCl) {
      		
      		cmbDetalleProductos.addItem(proveedor);
   			
   		}
      }
    
}

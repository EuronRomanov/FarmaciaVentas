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
import com.farmacia.entidades.Categoria;
import com.farmacia.entidades.Producto;
import com.farmacia.form.Main;
import com.farmacia.form.RenderTabla;





public class ProductoDao {
	private static Connection con=ConexionBD.conectar();
	
	private DefaultTableModel modelo = new DefaultTableModel();
	
	
	
	
	
	/*
	 * 
	 * insetar
	 */
	 public boolean registrarProducto(Producto cl){
		 PreparedStatement stmtDetalle=null;
		 ResultSet rs = null;
	        String sql = "INSERT INTO Producto (nombreProducto,"
	        		+ "codigobarra,"
	        		+ "precioCompra,"
	        		+ "precioVenta,"
	        		+ "cantidad,"
	        		+ "unidadMedida,"
	        		+ "presentacion,"
	        		+ "marca,"
	        		+ "fechaCaduca,"
	        		+ "observaciones,"
	        		+ "formaFarmaceutica,"
	        		+ "codCategoria,"
	        		+ "codProveedor) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        try {
	        	con.setAutoCommit(false);
	            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	            
	            ps.setString(1, cl.getNombreProducto());
	           ps.setString(2, cl.getCodigobarra());
	           ps.setDouble(3, cl.getPrecioCompra());
	           ps.setDouble(4, cl.getPrecioVenta());
	           ps.setInt(5, cl.getCantidad());
	           ps.setString(6, cl.getUnidadMedida());
	           ps.setDouble(7, cl.getPresentacion());
	           ps.setString(8, cl.getMarca());
	           ps.setString(9, cl.getFechaCaduca().toString());
	           ps.setString(10, cl.getObservaciones());
	           ps.setString(11, cl.getFormaFarmaceutica());
	           ps.setInt(12, cl.getCodCategoria());
	           ps.setInt(13, cl.getCodProveedor());
	            
	            ps.executeUpdate();
	            // get candidate id
	            rs = ps.getGeneratedKeys();
	            int productoId = 0;
	            if(rs.next())
	            	productoId = rs.getInt(1);
	            
	            
	            int width = 13;
	            stmtDetalle = con.prepareStatement("UPDATE Producto SET codigobarra=? WHERE codProducto=?");
	            stmtDetalle.setString(1,String.format("%0" + width + "d",productoId));
	            stmtDetalle.setInt(2,productoId);
            	stmtDetalle.executeUpdate();
	            
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
	 
	 public List ListarProducto(){
	       List<Producto> ListaCl = new ArrayList();
	       String sql = "SELECT * FROM Producto,Categoria,Proveedor "
	       		+ "WHERE Producto.codCategoria=Categoria.codCategoria "
	       		+ "AND Producto.codProveedor=Proveedor.codProveedor AND Producto.disposicion=1";
	       try {
	           
	    	   PreparedStatement ps = con.prepareStatement(sql);
	    	   ResultSet rs = ps.executeQuery();
	    	   DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	    	   DateTimeFormatter parser2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	           while (rs.next()) {      
	        	   
	               Producto cl = new Producto(rs.getInt("codProducto"),
	            		                      rs.getString("nombreProducto"), 
	            		                      rs.getString("codigobarra"), 
	            		                      rs.getDouble("precioCompra"),
	            		                      rs.getDouble("precioVenta"),
	            		                      rs.getInt("cantidad"), 
	            		                      rs.getString("unidadMedida"),
	            		                      rs.getDouble("presentacion"), 
	            		                      rs.getString("marca"), 
	           			LocalDateTime.parse(rs.getTimestamp("fechaRegistro").toString(),parser),
	        			LocalDate.parse(rs.getDate("fechaCaduca").toString(),parser2), 
	        			rs.getString("observaciones"),
	        			rs.getString("formaFarmaceutica"),
	        			rs.getString("nombreCategoria"), 
	        			 rs.getString("nombreEmpresa"));
	               
	              
	               
	               ListaCl.add(cl);
	           }
	       } catch (SQLException e) {
	           System.out.println(e.toString());
	       }
	       return ListaCl;
	   }
	 
	/**
     * search all clients in the tblClient whose name contains the @key
     * @param key
     * @return list of client whose name contains the @key
     */
    public void searchProducto(String key,JTable tblProducto){
        ArrayList<Producto> result = new ArrayList<Producto>();
        String sql = "SELECT * FROM Producto,Categoria,Proveedor"
        		+ " WHERE Producto.codCategoria=Categoria.codCategoria "
        		+ "AND Producto.codProveedor=Proveedor.codProveedor"
        		+ "AND Producto.disposicion=1"
        		+ " AND Producto.nombreProducto LIKE ? OR Producto.formaFarmaceutica LIKE ?";
        modelo = (DefaultTableModel) tblProducto.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[15];
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	ob[0]=rs.getInt("codProducto");
            	ob[1]= rs.getString("nombreProducto");
            	ob[2]=rs.getString("codigobarra"); 
            	ob[3]=rs.getDouble("precioCompra");
            	ob[4]=rs.getDouble("precioVenta");
            	ob[5]=rs.getInt("cantidad"); 
            	ob[6]=rs.getString("unidadMedida");
            	ob[7]=rs.getDouble("presentacion"); 
            	ob[8]=rs.getString("marca"); 
            	ob[9]=rs.getTimestamp("fechaRegistro").toString();
            	ob[10]=rs.getDate("fechaCaduca").toString().replaceFirst("T", " "); 
            	ob[11]=rs.getString("observaciones");
            	ob[12]=rs.getString("formaFarmaceutica");
            	ob[13]=rs.getInt("codCategoria");
            	ob[14]=rs.getInt("codProveedor");
            	
            	
               
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
        		+ "fechaCaduca=?,"
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
	           ps.setString(8, cl.getFechaCaduca().toString());
	           ps.setString(9, cl.getObservaciones());
	           ps.setInt(10, cl.getCodCategoria());
	           ps.setInt(11, cl.getCodProveedor());
	           ps.setString(12, cl.getFormaFarmaceutica());
            
            ps.setInt(13, cl.getCodProducto());

            ps.executeUpdate();
        }catch(Exception e){
        	System.out.println("Comprobrar Mtodo2"+cl.getFechaCaduca().toString());
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
        Object[] ob = new Object[15];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getCodProducto();
            ob[1] = ListarCl.get(i).getNombreProducto();
            ob[2] = ListarCl.get(i).getCodigobarra();
            ob[3] = ListarCl.get(i).getPrecioCompra();
            ob[4] = ListarCl.get(i).getPrecioVenta();
            ob[5] = ListarCl.get(i).getCantidad();
            ob[6] = ListarCl.get(i).getUnidadMedida();
            ob[7] = ListarCl.get(i).getPresentacion();
            ob[8] = ListarCl.get(i).getMarca();
            ob[9] = ListarCl.get(i).getFechaRegistro().toString().replaceFirst("T", " ");
            ob[10] = ListarCl.get(i).getFechaCaduca().toString();
            ob[11] = ListarCl.get(i).getObservaciones();
            ob[12] = ListarCl.get(i).getFormaFarmaceutica();
            ob[13] = ListarCl.get(i).getCategoria();
            ob[14] =ListarCl.get(i).getProveedor();
            modelo.addRow(ob);
        }
        tblProducto.setModel(modelo);
        
        

    }



	public Producto searchProductoId(int key ) {
		 
	        String sql = "SELECT * FROM Producto WHERE disposicion=1 AND codProducto=?";
	        Producto producto =null;
	        try{
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, key );
	            ResultSet rs = ps.executeQuery();
	            DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	            while(rs.next()){
	            	 producto = new Producto(rs.getInt("codProducto"),
		                      rs.getString("nombreProducto"), 
		                      rs.getString("codigobarra"), 
		                      rs.getDouble("precioCompra"),
		                      rs.getDouble("precioVenta"),
		                      rs.getInt("cantidad"), 
		                      rs.getString("unidadMedida"),
		                      rs.getDouble("presentacion"), 
		                      rs.getString("marca"), 
							LocalDateTime.parse(rs.getTimestamp("fechaRegistro").toString().replaceFirst("T", " "),parser ),
						    LocalDate.parse(rs.getTimestamp("fechaCaduca").toString(),parser ), 
						    rs.getString("observaciones"),
						    rs.getString("formaFarmaceutica"),
						    rs.getInt("codCategoria"), 
						    rs.getInt("codProveedor"));
	            	
	            	
	               
	               
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
    
	
	 public void agregarProductoProCodigo(String key,JTable tblVentas){
		 String sql = "SELECT * FROM Producto"
	        		+ " WHERE  disposicion=1 AND codProducto=? ";
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
		            	ob[0]=rs.getInt("codProducto");
		            	ob[1]= rs.getString("nombreProducto");
		            	 
		            	
		            	ob[2]=rs.getDouble("precioVenta");
		            	 
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
    	
    	int cantidad=Integer.parseInt(tblVentas.getValueAt(i, 3).toString());
    	tblVentas.setValueAt((cantidad+1), i, 3);
    	flag=true;
    	break;
		}
    	}
    	return flag;
    }
    
    public void cargarListaProductos(JComboBox cmbDetalleProductos) {
      	 List<Producto> ListarCl = this.ListarProducto();
      	cmbDetalleProductos.removeAllItems();
      	 for (Producto proveedor : ListarCl) {
      		cmbDetalleProductos.addItem(proveedor);
   			
   		}
      }
    
}

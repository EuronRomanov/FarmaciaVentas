package com.farmacia.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Producto;





public class ProductoDao {
	private static Connection con=ConexionBD.conectar();
	
	private DefaultTableModel modelo = new DefaultTableModel();
	
	
	
	
	
	/*
	 * 
	 * insetar
	 */
	 public boolean registrarProducto(Producto cl){
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
	            
	            PreparedStatement ps = con.prepareStatement(sql);
	            
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
	            
	            ps.execute();
	            return true;
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e.toString());
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
	       		+ "AND Producto.codProveedor=Proveedor.codProveedor";
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
    public ArrayList<Producto> searchProducto(String key){
        ArrayList<Producto> result = new ArrayList<Producto>();
        String sql = "SELECT * FROM Producto WHERE nombreProducto LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	Producto Producto = new Producto(rs.getInt("codProducto"),
	                      rs.getString("nombreProducto"), 
	                      rs.getString("codigobarra"), 
	                      rs.getDouble("precioCompra"),
	                      rs.getDouble("precioVenta"),
	                      rs.getInt("cantidad"), 
	                      rs.getString("unidadMedida"),
	                      rs.getDouble("presentacion"), 
	                      rs.getString("marca"), 
						LocalDateTime.parse(rs.getTimestamp("fechaRegistro").toString()),
					    LocalDate.parse(rs.getTimestamp("fechaCaduca").toString()), 
					    rs.getString("observaciones"),
					    rs.getString("formaFarmaceutica"),
					    rs.getInt("codCategoria"), 
					    rs.getInt("codProveedor"));
            	
            	
               
                result.add(Producto);
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
        return result;
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
        		+ "codProveedor=? WHERE codProducto=?";
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
            
            ps.setInt(12, cl.getCodProducto());

            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    /**
     * delete the client whose id is @id
     * @param id
     */
    public void deleteProducto(int id){
        String sql = "DELETE FROM Producto WHERE codProducto=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
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
            ob[9] = ListarCl.get(i).getFechaRegistro().toString();
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
		 
	        String sql = "SELECT * FROM Producto WHERE codProducto=?";
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
							LocalDateTime.parse(rs.getTimestamp("fechaRegistro").toString(),parser ),
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
    
    
}

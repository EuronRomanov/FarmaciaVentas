package com.farmacia.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Categoria;
import com.farmacia.entidades.Proveedor;





public class CategoriaDao {
	private static Connection con=ConexionBD.conectar();
	Object[][] data=new Object[][] {
	};
	String [] columnas=new String[] {
			"Codigo", "Nombre"
		};
	private DefaultTableModel modelo = new DefaultTableModel(data,columnas){
	    private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
	};
	
	
	
	
	
	/*
	 * 
	 * insetar
	 */
	 public boolean registrarCategoria(Categoria cl){
	        String sql = "INSERT INTO Categoria (nombreCategoria) VALUES (?)";
	        try {
	            
	            PreparedStatement ps = con.prepareStatement(sql);
	            
	            ps.setString(1, cl.getNombreCategoria());
	           
	            
	            ps.execute();
	            return true;
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e.toString());
	            return false;
	        }finally{
	           /* try {
	               con.close();
	            } catch (SQLException e) {
	                System.out.println(e.toString());
	            }*/
	        }
	    }
	
	
	 
	 /*
	  * listar
	  */
	 
	 public List ListarCategoria(){
	       List<Categoria> ListaCl = new ArrayList();
	       String sql = "SELECT * FROM Categoria order by codCategoria";
	       try {
	           
	    	   PreparedStatement ps = con.prepareStatement(sql);
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	               Categoria cl = new Categoria();
	               cl.setCodCategoria(rs.getInt("codCategoria"));
	               cl.setNombreCategoria(rs.getString("nombreCategoria"));
	               
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
    public DefaultTableModel searchCategoria(String key,JTable tblCategoria){
    	
    	DefaultTableModel modelo=new DefaultTableModel();
    	modelo=(DefaultTableModel) tblCategoria.getModel();
    	 modelo.setRowCount(0);
         String datos[]=new String[2];
         
        ArrayList<Categoria> result = new ArrayList<Categoria>();
        String sql = "SELECT * FROM Categoria WHERE nombreCategoria LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            /*	Categoria categoria = new Categoria();
            	categoria.setCodCategoria(rs.getInt("codCategoria"));
            	categoria.setNombreCategoria(rs.getString("nombreCategoria"));
               
                result.add(categoria);*/
                
                datos[0]=String.valueOf(rs.getInt("codCategoria"));
                datos[1]=rs.getString("nombreCategoria");
                modelo.addRow(datos);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
           /* try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
        }    
        return modelo;
    }
    
    /**
     * search all clients in the tblClient whose name contains the @key
     * @param key
     * @return list of client whose name contains the @key
     */
    public Categoria searchCategoriaId(int key){
    	Categoria categoria = new Categoria();
    	
        String sql = "SELECT * FROM Categoria WHERE codCategoria=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,  key );
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	
            	categoria.setCodCategoria(rs.getInt("codCategoria"));
            	categoria.setNombreCategoria(rs.getString("nombreCategoria"));
               
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
           /* try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
        }    
        return categoria;
    }
    /**
     * update the @client
     * @param client
     */
    public void editCategoria(Categoria categoria){
        String sql = "UPDATE Categoria SET nombreCategoria=? WHERE codCategoria=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getCodCategoria());

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
    
    /**
     * delete the client whose id is @id
     * @param id
     */
    public void deleteCategoria(int id){
        String sql = "DELETE FROM Categoria WHERE codCategoria=?";
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
    
    
    public void ListarCategoriaTable(JTable tblCategoria) {
    	
    	
        List<Categoria> ListarCl = this.ListarCategoria();
        modelo = (DefaultTableModel) tblCategoria.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getCodCategoria();
            ob[1] = ListarCl.get(i).getNombreCategoria();
           
            modelo.addRow(ob);
        }
        tblCategoria.setModel(modelo);

    }
    
    public void cargarListaCategorias(JComboBox cmbProductoCategoria) {
   	 List<Categoria> ListarCl = this.ListarCategoria();
   	cmbProductoCategoria.removeAllItems();
   	 for (Categoria proveedor : ListarCl) {
   		cmbProductoCategoria.addItem(proveedor);
			
		}
   }
}

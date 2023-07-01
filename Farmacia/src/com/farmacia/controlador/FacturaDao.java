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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Categoria;
import com.farmacia.entidades.Proveedor;
import com.farmacia.entidades.Usuario;
import com.farmacia.form.Main;
import com.farmacia.form.RenderTabla;
import com.farmacia.entidades.Factura;





public class FacturaDao {
	private static Connection con=ConexionBD.conectar();
	
	private DefaultTableModel modelo = new DefaultTableModel();
	
	
	
	
	
	/*
	 * 
	 * insetar
	 */
	 public boolean registrarProveedor(Proveedor cl){
	        String sql = "INSERT INTO Proveedor (nombreEmpresa,"
	        		+ "representante,"
	        		+ "direccion,"
	        		+ "celular,"
	        		+ "telefono,ruc) VALUES (?,?,?,?,?,?)";
	        try {
	            
	            PreparedStatement ps = con.prepareStatement(sql);
	            
	            ps.setString(1, cl.getNombreProveedor());
	           ps.setString(2, cl.getRepresentante());
	           ps.setString(3, cl.getDireccion());
	           ps.setString(4, cl.getCelular());
	           ps.setString(5, cl.getTelefono());
	           ps.setString(6, cl.getRuc());
	            
	            ps.execute();
	            return true;
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e.toString());
	            return false;
	        }finally{
	          /*  try {
	                con.close();
	            } catch (SQLException e) {
	                System.out.println(e.toString());
	            }*/
	        }
	    }
	
	
	 
	 /*
	  * listar
	  */
	 
	 public List listarFacturas(){
	       List<Factura> ListaCl = new ArrayList();
	       String sql = "SELECT * FROM Factura,Usuario"
	       		+ " WHERE Factura.codUsuario=Usuario.codUsuario";
	       try {
	    	   DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	    	   PreparedStatement ps = con.prepareStatement(sql);
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	              
	               
	               Factura cl=new Factura(rs.getInt("codFactura"),
	            		   LocalDateTime.parse(rs.getTimestamp("fecha").toString(),parser), 
	            		   rs.getString("ruc"),
	            		   rs.getString("cedula"), 
	            		   rs.getString("n_cliente"), 
	            		   rs.getString("observacion"),
	            		   rs.getDouble("subtotal"), 
	            		   rs.getDouble("total"), 
	            		   rs.getString("nombre"));
	               
	               
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
    public void searchFactura(String key, JTable tblProveedor){
       
        String sql = "SELECT * FROM Factura,Usuario"
        		+ "	WHERE Factura.codUsuario=Usuario.codUsuario n_cliente LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            modelo = (DefaultTableModel) tblProveedor.getModel();
            modelo.setRowCount(0);
            Object[] ob = new Object[10];
            while(rs.next()){
            	ob[0] =rs.getInt("codProveedor");
            	ob[1] =rs.getString("nombreEmpresa");
            	ob[2] =rs.getString("representante");
            	ob[3] =rs.getString("direccion");
            	ob[4] =rs.getString("celular");
            	ob[5] =rs.getString("telefono");
            	ob[6] =rs.getString("ruc");
            	ob[7] =rs.getString("ruc");
            	ob[8] =rs.getString("ruc");
            	ob[9] =rs.getString("ruc");
            	
            	modelo.addRow(ob);
               
                //result.add(Proveedor);
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          /*  try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
        }    
        //return result;
        tblProveedor.setModel(modelo);
    }
    
    
    
    /*
     * buscar por id
     */
    
    public Factura searchFacturaId(int key){
    	Factura factura= new Factura();
    	
        String sql = "SELECT * FROM Factura,Usuario WHERE Factura.codUsuario=Usuario.codUsuario AND codFactura=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,  key );
            ResultSet rs = ps.executeQuery();
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            while(rs.next()){
            	
            	factura = new Factura(rs.getInt("codFactura"),
	            		   LocalDateTime.parse(rs.getTimestamp("fecha").toString(),parser), 
	            		   rs.getString("ruc"),
	            		   rs.getString("cedula"), 
	            		   rs.getString("n_cliente"), 
	            		   rs.getString("observacion"),
	            		   rs.getDouble("subtotal"), 
	            		   rs.getDouble("total"), 
	            		   rs.getString("nombre"));;
               
                
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
        return factura;
    }
    
    
    /**
     * update the @client
     * @param client
     */
    public void editFactura(Factura factura){
        String sql = "UPDATE Factura SET ruc=?, "
        		+ "cedula=?,"
        		+ "n_cliente=?,"
        		+ "observacion=?,"
        		+ "subtotal=?,"
        		+ "total=?,"
        		+ "codUsuario=? WHERE codFactura=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, factura.getRuc());
            ps.setString(2, factura.getCedula());
            ps.setString(3, factura.getN_cliente());
            ps.setString(4, factura.getObervacion());
            ps.setDouble(5, factura.getSubtotal());
            ps.setDouble(6, factura.getTotal());
            ps.setInt(7, factura.getCodUsuario());
            
            ps.setInt(8, factura.getCodFactura());

            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          /*  try {
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
    public void deleteFactura(int id){
        String sql = "DELETE FROM Factura WHERE codFactura=?";
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
    
    
    public void ListarFacturaTable(JTable tblFacturas) {
        List<Factura> ListarCl = this.listarFacturas();
        modelo = (DefaultTableModel) tblFacturas.getModel();
        modelo.setRowCount(0);
        JButton button=new JButton("");
        button.setName("btnFacturaDetalle");
        button.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/icon-lupa.png")));
        
        Object[] ob = new Object[10];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getCodFactura();
            ob[1] = ListarCl.get(i).getFecha();
            ob[2] = ListarCl.get(i).getRuc();
            ob[3] = ListarCl.get(i).getCedula();
            ob[4] = ListarCl.get(i).getN_cliente();
            ob[5] = ListarCl.get(i).getObervacion();
            ob[6] = ListarCl.get(i).getSubtotal();
            ob[7] = ListarCl.get(i).getTotal();
            ob[8] = ListarCl.get(i).getUsuario();
            ob[9]=button;
            modelo.addRow(ob);
        }
        tblFacturas.setDefaultRenderer(Object.class,new RenderTabla());
        tblFacturas.setModel(modelo);
        tblFacturas.setRowHeight(30);

    }
    
    public void cargarListaVededores(JComboBox cmbFacturaUsuario) {
    	 List<Usuario> ListarCl = new UsuarioDao().ListarUsuario();
    	 cmbFacturaUsuario.removeAllItems();
    	 for (Usuario usuario : ListarCl) {
    		 cmbFacturaUsuario.addItem(usuario);
			
		}
    }
    
}

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
    public void searchProveedor(String key, JTable tblProveedor){
        ArrayList<Proveedor> result = new ArrayList<Proveedor>();
        String sql = "SELECT * FROM Proveedor WHERE nombreEmpresa LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            modelo = (DefaultTableModel) tblProveedor.getModel();
            modelo.setRowCount(0);
            Object[] ob = new Object[7];
            while(rs.next()){
            	ob[0] =rs.getInt("codProveedor");
            	ob[1] =rs.getString("nombreEmpresa");
            	ob[2] =rs.getString("representante");
            	ob[3] =rs.getString("direccion");
            	ob[4] =rs.getString("celular");
            	ob[5] =rs.getString("telefono");
            	ob[6] =rs.getString("ruc");
            	
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
    
    public Proveedor searchProveedorId(int key){
    	Proveedor proveedor = new Proveedor();
    	
        String sql = "SELECT * FROM Proveedor WHERE codProveedor=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,  key );
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	
            	proveedor = new Proveedor(rs.getInt("codProveedor"),
						rs.getString("nombreEmpresa"),
						rs.getString("representante"),
						rs.getString("direccion"),
						rs.getString("celular"),
						rs.getString("telefono"),
						rs.getString("ruc"));
               
                
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
        return proveedor;
    }
    
    
    /**
     * update the @client
     * @param client
     */
    public void editProveedor(Proveedor Proveedor){
        String sql = "UPDATE Proveedor SET nombreEmpresa=?, "
        		+ "representante=?,"
        		+ "direccion=?,"
        		+ "celular=?,"
        		+ "telefono=?,"
        		+ "ruc=? WHERE codProveedor=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Proveedor.getNombreProveedor());
            ps.setString(2, Proveedor.getRepresentante());
            ps.setString(3, Proveedor.getDireccion());
            ps.setString(4, Proveedor.getCelular());
            ps.setString(5, Proveedor.getTelefono());
            ps.setString(6, Proveedor.getRuc());
            
            ps.setInt(7, Proveedor.getCodProveedor());

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
    public void deleteProveedor(int id){
        String sql = "DELETE FROM Proveedor WHERE codProveedor=?";
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
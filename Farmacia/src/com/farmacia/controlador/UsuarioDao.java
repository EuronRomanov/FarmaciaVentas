package com.farmacia.controlador;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Usuario;





public class UsuarioDao {
	private static Connection con=ConexionBD.conectar();
	
	private DefaultTableModel modelo = new DefaultTableModel();
	
	
	
	
	
	/*
	 * 
	 * insetar
	 */
	 public boolean RegistrarUsuario(Usuario cl){
	        String sql = "INSERT INTO Usuario (nombre,password,estado,administrador,cedula) VALUES (?,?,?,?,?)";
	        try {
	            
	            PreparedStatement ps = con.prepareStatement(sql);
	            
	            ps.setString(1, cl.getNombre());
	            ps.setString(2, calculateHash("SHA3-512", cl.getPassword()));
	            ps.setInt(3, cl.getEstado());
	            ps.setInt(4, cl.getAdministrador());
	            ps.setString(5, cl.getCedula());
	            ps.execute();
	            return true;
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e.toString());
	            return false;
	        } finally{
	            try {
	                con.close();
	            } catch (SQLException e) {
	                System.out.println(e.toString());
	            }
	        }
	    }
	
	
	 
	 /*
	  * listar
	  */
	 
	 public List ListarUsuario(){
	       List<Usuario> ListaCl = new ArrayList();
	       String sql = "SELECT * FROM Usuario";
	       try {
	           
	    	   PreparedStatement ps = con.prepareStatement(sql);
	    	   ResultSet rs = ps.executeQuery();
	           while (rs.next()) {               
	               Usuario cl = new Usuario();
	               cl.setCodUsuario(rs.getInt("codUsuario"));
	               cl.setNombre(rs.getString("nombre"));
	               cl.setPassword(rs.getString("password"));
	               cl.setEstado(rs.getInt("estado"));
	               cl.setAdministrador(rs.getInt("administrador"));
	               cl.setCedula(rs.getString("cedula"));
	               
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
    public ArrayList<Usuario> searchUsuario(String key){
        ArrayList<Usuario> result = new ArrayList<Usuario>();
        String sql = "SELECT * FROM Usuario WHERE nombre LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	Usuario Usuario = new Usuario(rs.getInt("codUsuario"),
            			 rs.getString("nombre"), 
            			rs.getString("password"), 
            			 rs.getInt("estado"), 
            			  rs.getInt("administrador"), 
            			 rs.getString("cedula"));
            	
            	
            	
               
                result.add(Usuario);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }    
        return result;
    }
    
    /**
     * update the @client
     * @param client
     */
    public void editUsuario(Usuario Usuario,String newPassword){
        String sql = "UPDATE Usuario SET nombre=? "
        		+ "password=?,"
        		+ "estado=?,"
        		+ "administrador=?,"
        		+ "cedula=? WHERE codUsuario=?";
        String nwp="";
        try{
        	if (newPassword.length()<1) {
        		nwp=Usuario.getPassword();
			} else {
				nwp=this.calculateHash("SHA3-512", newPassword);
			}
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Usuario.getNombre());
            ps.setString(2, nwp);
            ps.setString(3, Usuario.getCedula());
            ps.setInt(4, Usuario.getAdministrador());
            ps.setInt(5, Usuario.getCodUsuario());
            
            ps.setInt(6, Usuario.getCodUsuario());

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
    public void deleteUsuario(int id){
        String sql = "DELETE FROM Usuario WHERE codUsuario=?";
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
    
    
    public void ListarUsuarioTable(JTable tblUsuario) {
        List<Usuario> ListarCl = this.ListarUsuario();
        modelo = (DefaultTableModel) tblUsuario.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getCodUsuario();
            ob[1] = ListarCl.get(i).getNombre();
            ob[2] = ListarCl.get(i).getPassword();
            ob[3] = ListarCl.get(i).getAdministrador();
            ob[4] = ListarCl.get(i).getEstado();
            ob[5] = ListarCl.get(i).getCedula();
           
            modelo.addRow(ob);
        }
        tblUsuario.setModel(modelo);

    }
    
    private static String calculateHash(String algorithm, String content)  {
        MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
			 messageDigest.update(content.getBytes(StandardCharsets.UTF_8));
		        byte[] hash = messageDigest.digest();
		        return HexFormat.of().formatHex(hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
       
    }
    
    
}

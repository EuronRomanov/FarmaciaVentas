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
import com.farmacia.bd.DBUtil;
import com.farmacia.entidades.Usuario;





public class UsuarioDao {
	//private static Connection con=ConexionBD.conectar();
	private  Connection con=new DBUtil().getConexion();
	private DefaultTableModel modelo = new DefaultTableModel();
	
	
	
	
	
	/*
	 * 
	 * insetar
	 */
	 public boolean registrarUsuario(Usuario cl){
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
    public void searchUsuario(String key,JTable tblUsuario){
       
        String sql = "SELECT * FROM Usuario WHERE disposicion=1 AND nombre LIKE ? OR cedula LIKE ? ";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            modelo = (DefaultTableModel) tblUsuario.getModel();
            modelo.setRowCount(0);
            Object[] ob = new Object[5];
            while(rs.next()){
            	
            	 ob[0] = rs.getInt("codUsuario");
                 ob[1] = rs.getString("nombre");
                
                 ob[2] = esEstado(rs.getInt("estado"));
                 ob[3] = esAdministrador( rs.getInt("administrador")) ;
                 ob[4] = rs.getString("cedula");
                
                 modelo.addRow(ob);
               
               
            }
            tblUsuario.setModel(modelo);
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
    public void editUsuario(Usuario usuario,String newPassword){
        String sql = "";
        String nwp="";
        try{
        	if (newPassword.length()<1) {
        		sql = "UPDATE Usuario SET nombre=? ,"
                		+ "estado=?,"
                		+ "administrador=?,"
                		+ "cedula=? WHERE codUsuario=?";
			} else {
				
				sql = "UPDATE Usuario SET nombre=?,"
		        		+ "estado=?,"
		        		+ "administrador=?,"
		        		+ "cedula=? ,"
		        		+ "password=? WHERE codUsuario=?";
			}
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getEstado());
            ps.setInt(3, usuario.getAdministrador());
            ps.setString(4, usuario.getCedula());
            
            if (newPassword.length()<1) {
            	
                 ps.setInt(5, usuario.getCodUsuario());
            }else {
            	 ps.setString(5, calculateHash("SHA3-512", newPassword));
                 ps.setInt(6, usuario.getCodUsuario());
            }
           

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
    public void deleteUsuario(int id){
        String sql = "UPDATE Usuario SET disposicion=0 WHERE codUsuario=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
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
    
  
   public Usuario searchUsuarioId(String key){
	   Usuario usuario=null;
       String sql = "SELECT * FROM Usuario WHERE disposicion=1  AND codUsuario=?";
       try{
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1,  key );
           ResultSet rs = ps.executeQuery();

           while(rs.next()){
            usuario = new Usuario(rs.getInt("codUsuario"),
           			 rs.getString("nombre"), 
           			rs.getString("password"), 
           			 rs.getInt("estado"), 
           			  rs.getInt("administrador"), 
           			 rs.getString("cedula"));
           	
           	
           	
              
             
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
       return  usuario;
   }
    public void listarUsuarioTable(JTable tblUsuario) {
        List<Usuario> ListarCl = this.ListarUsuario();
        modelo = (DefaultTableModel) tblUsuario.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getCodUsuario();
            ob[1] = ListarCl.get(i).getNombre();
            ob[2] = esEstado(ListarCl.get(i).getEstado());
            ob[3] = esAdministrador(ListarCl.get(i).getAdministrador()) ;
            ob[4] = ListarCl.get(i).getCedula();
           
            modelo.addRow(ob);
        }
        tblUsuario.setModel(modelo);

    }
    
    private String  esAdministrador(int admin) {
		String estado="";
		
		switch (admin) {
		case 0:
			estado="No";
			break;
		case 1:
			estado="Si";
			break;
		default:
			break;
		}
		return estado;
	}

    public String  esAdministradorString(int admin) {
  		String estado="";
  		
  		switch (admin) {
  		case 0:
  			estado="Vendedor";
  			break;
  		case 1:
  			estado="Administrador";
  			break;
  		default:
  			break;
  		}
  		return estado;
  	}


	private String esEstado(int key) {
String estado="";
		
		switch (key) {
		case 0:
			estado="Activo";
			break;
		case 1:
			estado="Deshabilitado";
			break;
		default:
			break;
		}
		return estado;
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
    
	 public Usuario loginUsuario(String user, String pass){
		 Usuario usuario=null;
	       String sql = "SELECT * FROM Usuario WHERE disposicion=1 AND estado=0  AND cedula=? AND password=? ";
	       try{
	           PreparedStatement ps = con.prepareStatement(sql);
	           ps.setString(1,  user );
	           ps.setString(2,  calculateHash("SHA3-512",pass));
	           ResultSet rs = ps.executeQuery();

	           while(rs.next()){
	           
	           	
	            usuario = new Usuario();
	            usuario.setCodUsuario(rs.getInt("codUsuario"));
	            usuario.setNombre(rs.getString("nombre"));
	            usuario.setAdministrador(rs.getInt("administrador"));
	             
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
	       
	       return usuario;
	    }
	    
}

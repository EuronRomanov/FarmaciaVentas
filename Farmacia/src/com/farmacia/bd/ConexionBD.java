package com.farmacia.bd;

import java.sql.*;


public class ConexionBD {

    public static String url = "jdbc:mysql://localhost/farmacia?serverTimezone=UTC";
    public static String usuario = "root";
    public static String clave = "sacapalca";
    public static String clase = "com.mysql.cj.jdbc.Driver";
    private static Connection conexion = null;
    public static Connection conectar() {
       

        try {
            Class.forName(clase);
            conexion = DriverManager.getConnection(url, usuario, clave);
            //System.out.println("Conexion Establecida");
            //JOptionPane.showMessageDialog(null, "CONEXION EXITOSA");
        } catch (ClassNotFoundException e) {
			System.out.println("Error >> Driver no Instalado" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexión con la BD" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error >> general : " + e.getMessage());
		} 
        return conexion;
    }
    public static Connection CerrarConexion()
    {
        try {
			if (conexion.isClosed()) {
				try {
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        return conexion;
    }
}

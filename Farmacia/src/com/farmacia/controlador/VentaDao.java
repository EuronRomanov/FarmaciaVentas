package com.farmacia.controlador;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.farmacia.bd.ConexionBD;
import com.farmacia.entidades.Factura;
import com.farmacia.utils.GenerdorDocumentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class VentaDao {
	private static Connection con=ConexionBD.conectar();
	
	public void registarVenta(Factura factura,JTable productos) {
        PreparedStatement stmtFactura =null;
        PreparedStatement stmtDetalle=null; 
         
        ResultSet rs = null;
		 try {
			            //se deshabilita el modo de confirmación automática
			            con.setAutoCommit(false);  
			            stmtFactura = con.prepareStatement("INSERT INTO Factura (ruc,cedula,n_cliente,observacion,total,codUsuario) VALUES( ?, ?,?,?,?,? );",Statement.RETURN_GENERATED_KEYS);            
			            stmtDetalle = con.prepareStatement("INSERT INTO Detalle (cantidad,codProducto,v_total,codFactura)VALUES( ?, ?, ?,? );");
			           
			            
			            //insert factura
			            stmtFactura.setString(1,factura.getRuc());
			            stmtFactura.setString(2,factura.getCedula());
			            stmtFactura.setString(3,factura.getN_cliente());
			            stmtFactura.setString(4,factura.getObervacion());
			            stmtFactura.setDouble(5,factura.getTotal());
			            stmtFactura.setInt(6,factura.getCodUsuario());
			            stmtFactura.executeUpdate();
			            
			            ////
			         // get candidate id
			            rs = stmtFactura.getGeneratedKeys();
			            int facturaId = 0;
			            if(rs.next())
			            	facturaId = rs.getInt(1);
			            ///insertar detalle factura
			            for (int i = 0; i < productos.getRowCount(); i++) {
			            	int codProducto=Integer.parseInt(productos.getValueAt(i, 0).toString());
			            	double precio=Double.parseDouble(productos.getValueAt(i, 2).toString());
			            	int cantidad=Integer.parseInt(productos.getValueAt(i, 3).toString());
			            	double valor=cantidad*precio;
			            	stmtDetalle.setInt(1,cantidad);
			            	stmtDetalle.setInt(2,codProducto);
			            	stmtDetalle.setDouble(3,valor);
			            	stmtDetalle.setInt(4,facturaId);
			            	stmtDetalle.executeUpdate();
						}
			            
			            
			            con.commit();
			            new GenerdorDocumentos().generarTicket(facturaId);     
			} catch (SQLException ex) {
			  System.err.println("ERROR: " + ex.getMessage());
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
			}
	}
	
	
	
	public void totalizar(JTable tblVentas,JLabel lblTotalpagar) {
		double t=0;
		double p=0;
		double c=0.0;
		if (tblVentas.getRowCount()>0) {
			for (int i = 0; i < tblVentas.getRowCount(); i++) {
				p=Double.parseDouble(tblVentas.getValueAt(i, 2).toString())*Double.parseDouble(tblVentas.getValueAt(i, 3).toString());
				t+=p;
				lblTotalpagar.setText(getTwoDecimals(String.valueOf(t)));
			}
		}else {
			lblTotalpagar.setText(getTwoDecimals(String.valueOf(c)));
		}
		
	}
	
	private  String getTwoDecimals(String valor){
	    DecimalFormat df = new DecimalFormat("0.00");
	    return df.format(Double.parseDouble(valor));
	}
}

package com.farmacia.controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.farmacia.bd.ConexionBD;
import com.farmacia.bd.DBUtil;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Factura;
import com.farmacia.utils.GenerdorDocumentos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;

public class VentaDao {
	//private static Connection con=ConexionBD.conectar();
	private  Connection con=new DBUtil().getConexion();
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
	
	 public void agregarDetalle(Detalle detalle)
	   {
		 
		
	       int resultado;
	       try {            
	            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
	            CallableStatement proc = con.prepareCall(" CALL proc_agregarNuevoDetalleFactura(?,?,?,?,?) ");
	            //se cargan los parametros de entrada
	            proc.setInt("cantidadIn", detalle.getCantidad());//Tipo String
	            proc.setInt("codProductoIn", detalle.getCodProducto());//Tipo entero
	            proc.setDouble("v_totalIn", detalle.getV_total());//Tipo entero
	            proc.setInt("codFacturaIn", detalle.getCodFactura());//Tipo entero
	            // parametros de salida
	            proc.registerOutParameter("respuesta", Types.INTEGER);//Tipo String
	            // Se ejecuta el procedimiento almacenado
	            proc.execute();            
	            // devuelve el valor del parametro de salida del procedimiento
	            resultado = proc.getInt("respuesta");
	            if (resultado ==0) {
	            	JOptionPane.showMessageDialog(null, "Revise stock de este producto");
				}
	        } 
	       catch (Exception e) {                  
	            System.out.println(e);
	       }
	       //return resultado;
	   }
	
	 public void agregarDetalleExistente(Detalle detalle)
	   {
		 
		
	       int resultado;
	       try {            
	            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
	            CallableStatement proc = con.prepareCall(" CALL proc_agregarRepetidoDetalleFactura(?,?,?,?,?,?) ");
	            //se cargan los parametros de entrada
	            proc.setInt("cantidadIn", detalle.getCantidad());//Tipo String
	            proc.setInt("codProductoIn", detalle.getCodProducto());//Tipo entero
	            proc.setDouble("v_totalIn", detalle.getV_total());//Tipo entero
	            proc.setInt("codFacturaIn", detalle.getCodFactura());//Tipo entero
	            proc.setInt("codCarritoIn", detalle.getCodCarrito());//Tipo entero
	            // parametros de salida
	            proc.registerOutParameter("respuesta", Types.INTEGER);//Tipo String
	            // Se ejecuta el procedimiento almacenado
	            proc.execute();            
	            // devuelve el valor del parametro de salida del procedimiento
	            resultado = proc.getInt("respuesta");
	            if (resultado ==0) {
	            	JOptionPane.showMessageDialog(null, "Revise stock de este producto");
				}
	        } 
	       catch (Exception e) {                  
	            System.out.println(e);
	       }
	       //return resultado;
	   }
	
	
	private  String getTwoDecimals(String valor){
	    DecimalFormat df = new DecimalFormat("0.00");
	    return df.format(Double.parseDouble(valor));
	}
}

package com.farmacia.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Producto {
    private int codProducto ;
    private String  nombreProducto;
   
    private double  precioCompra ;
    private double precioVenta ;
    private int cantidad ;
    private String unidadMedida ;
    private double presentacion;
    private String marca ;
    private LocalDateTime fechaRegistro ;
    
    private String observaciones ;
    private String formaFarmaceutica;
    private int  codCategoria ;
    private int  codProveedor;
    private String proveedor;
    private String categoria;
	public Producto() {
		super();
	}
	
	
	
	
	









	public Producto(String nombreProducto,  double precioCompra, double precioVenta, 
			String unidadMedida, double presentacion, String marca, String observaciones,String formaFarmaceutica,
			int codCategoria, int codProveedor) {
		super();
		this.nombreProducto = nombreProducto;
		
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		
		this.unidadMedida = unidadMedida;
		this.presentacion = presentacion;
		this.marca = marca;
		
		this.observaciones = observaciones;
		this.codCategoria = codCategoria;
		this.codProveedor = codProveedor;
		this.formaFarmaceutica=formaFarmaceutica;
	}














	







	public Producto(int codProducto, String nombreProducto,  double precioCompra, double precioVenta,
			int cantidad, String unidadMedida, double presentacion, String marca, LocalDateTime fechaRegistro,
			 String observaciones,String formaFarmaceutica, String proveedor, String categoria) {
		super();
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.presentacion = presentacion;
		this.marca = marca;
		this.fechaRegistro = fechaRegistro;
		
		this.observaciones = observaciones;
		this.proveedor = proveedor;
		this.categoria = categoria;
		this.formaFarmaceutica=formaFarmaceutica;
	}














	public Producto(int codProducto, String nombreProducto,  double precioCompra, double precioVenta,
			int cantidad, String unidadMedida, double presentacion, String marca, LocalDateTime fechaRegistro,
			 String observaciones,String formaFarmaceutica, int codCategoria, int codProveedor) {
		super();
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.presentacion = presentacion;
		this.marca = marca;
		this.fechaRegistro = fechaRegistro;
	
		this.observaciones = observaciones;
		this.codCategoria = codCategoria;
		this.codProveedor = codProveedor;
		this.formaFarmaceutica=formaFarmaceutica;
	}
	


	public Producto(int codProducto, String nombreProducto, double precioCompra, double precioVenta, int cantidad,
			String unidadMedida, double presentacion, String marca,  
			String observaciones, String formaFarmaceutica, int codCategoria, int codProveedor) {
		super();
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.presentacion = presentacion;
		this.marca = marca;
		
		
		this.observaciones = observaciones;
		this.formaFarmaceutica = formaFarmaceutica;
		this.codCategoria = codCategoria;
		this.codProveedor = codProveedor;
	}














	














	public int getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public double getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(double presentacion) {
		this.presentacion = presentacion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	public int getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}







	public String getProveedor() {
		return proveedor;
	}







	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}







	public String getCategoria() {
		return categoria;
	}







	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}














	public String getFormaFarmaceutica() {
		return formaFarmaceutica;
	}














	public void setFormaFarmaceutica(String formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}














	@Override
	public String toString() {
		return  nombreProducto+" "+this.formaFarmaceutica+" "+this.presentacion+" " +this.unidadMedida;
	}
	
	
    
    
    
}

package com.farmacia.entidades;

import java.time.LocalDateTime;

public class Producto {
    private int codProducto ;
    private String  nombreProducto;
    private String  codigobarra ;
    private double  precioCompra ;
    private double precioVenta ;
    private int cantidad ;
    private String unidadMedida ;
    private double presentacion;
    private String marca ;
    private LocalDateTime fechaRegistro ;
    private LocalDateTime fechaCaduca ;
    private String observaciones ;
    private int  codCategoria ;
    private int  codProveedor;
	public Producto() {
		super();
	}
	public Producto(int codProducto, String nombreProducto, String codigobarra, double precioCompra, double precioVenta,
			int cantidad, String unidadMedida, double presentacion, String marca, LocalDateTime fechaRegistro,
			LocalDateTime fechaCaduca, String observaciones, int codCategoria, int codProveedor) {
		super();
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		this.codigobarra = codigobarra;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.presentacion = presentacion;
		this.marca = marca;
		this.fechaRegistro = fechaRegistro;
		this.fechaCaduca = fechaCaduca;
		this.observaciones = observaciones;
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
	public String getCodigobarra() {
		return codigobarra;
	}
	public void setCodigobarra(String codigobarra) {
		this.codigobarra = codigobarra;
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
	public LocalDateTime getFechaCaduca() {
		return fechaCaduca;
	}
	public void setFechaCaduca(LocalDateTime fechaCaduca) {
		this.fechaCaduca = fechaCaduca;
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
    
    
    
}

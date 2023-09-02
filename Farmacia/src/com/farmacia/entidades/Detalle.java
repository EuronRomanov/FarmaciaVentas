package com.farmacia.entidades;

public class Detalle {
    private  int codCarrito ;
    private  int cantidad ;
    private int codProducto ;
    private  double v_total;
    private  int  codFactura;
    private String producto;
    private double precioVenta;
    private int codCodProductoBodega;
	public Detalle() {
		super();
	}
	public Detalle(int codCarrito, int cantidad, int codProducto, double v_total, int codFactura) {
		super();
		this.codCarrito = codCarrito;
		this.cantidad = cantidad;
		this.codProducto = codProducto;
		this.v_total = v_total;
		this.codFactura = codFactura;
	}
	
	
	public Detalle(int cantidad, int codProducto, double v_total, int codFactura) {
		super();
		this.cantidad = cantidad;
		this.codProducto = codProducto;
		this.v_total = v_total;
		this.codFactura = codFactura;
	}
	
	
	
	public Detalle(int codCarrito, int cantidad, String producto, double v_total, double precioVenta) {
		super();
		this.codCarrito = codCarrito;
		this.cantidad = cantidad;
		this.producto = producto;
		this.v_total = v_total;
		this.precioVenta = precioVenta;
	}
	public Detalle(int codCarrito, int cantidad, String producto, double v_total, double precioVenta,int codCodProductoBodega) {
		super();
		this.codCarrito = codCarrito;
		this.cantidad = cantidad;
		this.producto = producto;
		this.v_total = v_total;
		this.precioVenta = precioVenta;
		this.codCodProductoBodega=codCodProductoBodega;
	}
	
	
	
	public void setCodCodProductoBodega(int codCodProductoBodega) {
		this.codCodProductoBodega = codCodProductoBodega;
	}
	public int getCodCarrito() {
		return codCarrito;
	}
	public void setCodCarrito(int codCarrito) {
		this.codCarrito = codCarrito;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}
	public double getV_total() {
		return v_total;
	}
	public void setV_total(double v_total) {
		this.v_total = v_total;
	}
	public int getCodFactura() {
		return codFactura;
	}
	public void setCodFactura(int codFactura) {
		this.codFactura = codFactura;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public int getCodCodProductoBodega() {
		return codCodProductoBodega;
	}
	@Override
	public String toString() {
		return "Detalle [codCarrito=" + codCarrito + ", cantidad=" + cantidad + ", codProducto=" + codProducto
				+ ", v_total=" + v_total + ", codFactura=" + codFactura + ", producto=" + producto + ", precioVenta="
				+ precioVenta + ", codCodProductoBodega=" + codCodProductoBodega + "]";
	}
	
    
	
	
    
}

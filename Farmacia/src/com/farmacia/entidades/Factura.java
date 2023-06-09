package com.farmacia.entidades;

import java.time.LocalDateTime;

public class Factura {
    private int codFactura ;
    private LocalDateTime fecha ;
    private String ruc;
    private String cedula ;
    private String n_cliente ;
    private String obervacion;
    private double subtotal;
    private double total;
    private int  codUsuario;
	public Factura() {
		super();
	}
	public Factura(int codFactura, LocalDateTime fecha, String ruc, String cedula, String n_cliente, String obervacion,
			double subtotal, double total, int codUsuario) {
		super();
		this.codFactura = codFactura;
		this.fecha = fecha;
		this.ruc = ruc;
		this.cedula = cedula;
		this.n_cliente = n_cliente;
		this.obervacion = obervacion;
		this.subtotal = subtotal;
		this.total = total;
		this.codUsuario = codUsuario;
	}
	public int getCodFactura() {
		return codFactura;
	}
	public void setCodFactura(int codFactura) {
		this.codFactura = codFactura;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getN_cliente() {
		return n_cliente;
	}
	public void setN_cliente(String n_cliente) {
		this.n_cliente = n_cliente;
	}
	public String getObervacion() {
		return obervacion;
	}
	public void setObervacion(String obervacion) {
		this.obervacion = obervacion;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
    
    
}

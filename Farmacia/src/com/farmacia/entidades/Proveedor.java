package com.farmacia.entidades;

public class Proveedor {
    private int codProveedor ;
    private String nombreProveedor ;
    private String  representante ;
    private String direccion ;
    private String celular ;
    private String telefono ;
	public Proveedor() {
		super();
	}
	public Proveedor( String nombreProveedor, String representante, String direccion, String celular,
			String telefono) {
		super();
		this.codProveedor = codProveedor;
		this.nombreProveedor = nombreProveedor;
		this.representante = representante;
		this.direccion = direccion;
		this.celular = celular;
		this.telefono = telefono;
	}
	public Proveedor(int codProveedor, String nombreProveedor, String representante, String direccion, String celular,
			String telefono) {
		super();
		this.codProveedor = codProveedor;
		this.nombreProveedor = nombreProveedor;
		this.representante = representante;
		this.direccion = direccion;
		this.celular = celular;
		this.telefono = telefono;
	}
	public int getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
    
    
}

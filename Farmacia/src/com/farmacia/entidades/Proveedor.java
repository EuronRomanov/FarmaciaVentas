package com.farmacia.entidades;

public class Proveedor {
    private int codProveedor ;
    private String nombreEmpresa ;
    private String  representante ;
    private String direccion ;
    private String celular ;
    private String telefono ;
	public Proveedor() {
		super();
	}
	public Proveedor(int codProveedor, String nombreEmpresa, String representante, String direccion, String celular,
			String telefono) {
		super();
		this.codProveedor = codProveedor;
		this.nombreEmpresa = nombreEmpresa;
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
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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
    
    
}

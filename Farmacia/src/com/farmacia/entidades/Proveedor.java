package com.farmacia.entidades;

public class Proveedor {
    private int codProveedor ;
    private String nombreProveedor ;
    private String  representante ;
    private String direccion ;
    private String celular ;
    private String telefono ;
    private String ruc;
    
	public Proveedor() {
		super();
	}
	
	public Proveedor(String nombreProveedor, String representante, String direccion, String celular, String telefono,
			String ruc) {
		super();
		this.nombreProveedor = nombreProveedor;
		this.representante = representante;
		this.direccion = direccion;
		this.celular = celular;
		this.telefono = telefono;
		this.ruc = ruc;
	}

	public Proveedor(int codProveedor, String nombreProveedor, String representante, String direccion, String celular,
			String telefono, String ruc) {
		super();
		this.codProveedor = codProveedor;
		this.nombreProveedor = nombreProveedor;
		this.representante = representante;
		this.direccion = direccion;
		this.celular = celular;
		this.telefono = telefono;
		this.ruc = ruc;
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@Override
	public String toString() {
		return  nombreProveedor ;
	}
    
    
}

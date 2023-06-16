package com.farmacia.entidades;

public class Categoria {
	  private int codCategoria ;
	  private String  nombreCategoria ;
	public Categoria() {
		super();
	}
	public Categoria( String nombreCategoria) {
		super();
		
		this.nombreCategoria = nombreCategoria;
	}
	public Categoria(int codCategoria, String nombreCategoria) {
		super();
		this.codCategoria = codCategoria;
		this.nombreCategoria = nombreCategoria;
	}
	public int getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	  
	  
	}
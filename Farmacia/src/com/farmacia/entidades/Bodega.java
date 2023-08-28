package com.farmacia.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bodega {
	private int codBodega;
private int cantidadIngresada;
private LocalDateTime fechaIngreso;
private LocalDate fechaCaducidad;
private LocalDate fechaCuandoCaducidad;
private String caduca;
private String codigoBarra;
private int codProducto;
public Bodega() {
	super();
}
public Bodega(int cantidadIngresada, LocalDateTime fechaIngreso, LocalDate fechaCaducidad, String caduca) {
	super();
	this.cantidadIngresada = cantidadIngresada;
	this.fechaIngreso = fechaIngreso;
	this.fechaCaducidad = fechaCaducidad;
	this.caduca = caduca;
}
public Bodega(int codBodega, int cantidadIngresada, LocalDateTime fechaIngreso,LocalDate fechaCaducidad, LocalDate fechaCuandoCaducidad) {
	this.codBodega=codBodega;
	this.cantidadIngresada = cantidadIngresada;
	this.fechaIngreso = fechaIngreso;
	this.fechaCaducidad = fechaCaducidad;
	this.fechaCuandoCaducidad=fechaCuandoCaducidad;
	
}




public Bodega(int codBodega, int cantidadIngresada, LocalDate fechaCaducidad) {
	super();
	this.codBodega = codBodega;
	this.cantidadIngresada = cantidadIngresada;
	this.fechaCaducidad = fechaCaducidad;
}
public Bodega(int codBodega, int cantidadIngresada, LocalDate fechaCaducidad, String codigoBarra) {
	super();
	this.codBodega = codBodega;
	this.cantidadIngresada = cantidadIngresada;
	this.fechaCaducidad = fechaCaducidad;
	this.codigoBarra = codigoBarra;
}


public Bodega(int cantidadIngresada, LocalDate fechaCaducidad, int codProducto) {
	super();
	this.cantidadIngresada = cantidadIngresada;
	this.fechaCaducidad = fechaCaducidad;
	this.codProducto = codProducto;
}
public int getCantidadIngresada() {
	return cantidadIngresada;
}
public LocalDateTime getFechaIngreso() {
	return fechaIngreso;
}
public LocalDate getFechaCaducidad() {
	return fechaCaducidad;
}
public String getCaduca() {
	return caduca;
}
public int getCodBodega() {
	return codBodega;
}
public LocalDate getFechaCuandoCaducidad() {
	return fechaCuandoCaducidad;
}
public String getCodigoBarra() {
	return codigoBarra;
}
public int getCodProducto() {
	return codProducto;
}
@Override
public String toString() {
	return "" + codBodega + "- cantidad=" + cantidadIngresada + " fechaCaducidad="
			+ fechaCaducidad.toString().replaceFirst("T", " ") ;
}



}

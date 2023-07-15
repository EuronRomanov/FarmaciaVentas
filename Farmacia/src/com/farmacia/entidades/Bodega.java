package com.farmacia.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bodega {
private int cantidadIngresada;
private LocalDateTime fechaIngreso;
private LocalDate fechaCaducidad;
private String caduca;
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



}

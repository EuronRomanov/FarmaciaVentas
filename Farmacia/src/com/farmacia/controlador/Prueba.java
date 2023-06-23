package com.farmacia.controlador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.farmacia.utils.GenerdorDocumentos;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		GenerdorDocumentos generador=new GenerdorDocumentos();
		//generador.generarPDFs("1999999999999", 13);
		LocalDateTime hora = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        System.out.println(hora.format(f));
		
	}

}

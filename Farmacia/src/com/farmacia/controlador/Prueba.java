package com.farmacia.controlador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.farmacia.utils.GenerdorDocumentos;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		GenerdorDocumentos generador=new GenerdorDocumentos();
		
		generador.generarTicketTest(1);
		
	}

}

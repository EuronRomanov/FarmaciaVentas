package com.farmacia.controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fecha="2023-06-20 16:25:23.0";
		   DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime dateTime=LocalDateTime.parse(fecha,parser);
	}

}

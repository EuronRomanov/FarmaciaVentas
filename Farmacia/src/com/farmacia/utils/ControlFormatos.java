package com.farmacia.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import javax.swing.text.MaskFormatter;

public class ControlFormatos {

	
	public boolean hayEspaciosVacios(String... espacios) {
		boolean flag=false;
		for (int i = 0; i < espacios.length; i++) {
			if (espacios[i]==null ) {
				flag=true;
				break;
			}else if (espacios[i].isBlank()  ) {
				flag=true;
				break;
			}
		}
		
		return flag;
	}
	
	public boolean validarSoloLetras(int key) {
		boolean mayusculas=key>= 65 && key<=90;
		boolean minusculas=key>=97 && key<=122;
		boolean espacio=key==32;
		boolean tilde=key>=160 && key<=165;
		boolean tildeE=key==233 ;
		boolean tildeEm=key==201;
		boolean tildeA=key==225;
		boolean tildeAm=key==193;
		boolean tildeI=key==237;
		boolean tildeIm=key==205;
		boolean tildeO=key==243;
		boolean tildeOm=key==211;
		boolean tildeU=key==250;
		boolean tildeUm=key==218;
		
		return (!(minusculas|| 
				mayusculas || 
				espacio|| 
				tilde|| 
				tildeA|| 
				tildeAm|| 
				tildeI || 
				tildeU|| 
				tildeUm|| 
				tildeIm|| 
				tildeO|| 
				tildeOm || 
				tildeE|| 
				tildeEm ));
	}
	
	public boolean validarNumerosEnteros(int key) {
		boolean numero=key>=48 && key<=57;
		return (!(numero));
	}
	public boolean validarNumerosDecimal(int key) {
		boolean numero=key>=48 && key<=57;
		boolean punto=key==46;
		return (!(numero || 
				punto ));
	}

	public MaskFormatter getFormatoPrecio() {
		MaskFormatter mascara=null;
		try {
		 mascara=new MaskFormatter("##.##");
		 mascara.setPlaceholderCharacter('0');
		 mascara.setOverwriteMode(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mascara;
	}

	public Date cambiarLocaldateToDate(LocalDate fechaCaduca) {
		Instant insta=Timestamp.valueOf(fechaCaduca.format(DateTimeFormatter.ofPattern("yyy-MM-dd"))).toInstant();
		return  Date.from(insta);
	}
	
	public boolean validarFormatoFecha(Date strDate) {
		if (strDate.toString().trim().equals("")) {
			return false;
		} else {
               DateFormat sdfrmt=new SimpleDateFormat("yyyy-MM-dd");
               try {
				Date javaDate=strDate;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
               return true;
		}
	}
	
	public LocalDate fromDateToLocalDate(Date date) {
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
	public Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	public boolean controlarCedula(String cedula) {
		if (cedula.length()==10) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	public String darFormatoNumeroCeros(int valor) {
		int width = 13;
		int valorCompare=1000000000;
		 if (valorCompare<valor) {
			return String.valueOf(valor);
		} else {
             return String.format("%0" + width + "d", valor);
		}
       
	}
	
	public String redondearDosDecimales(double valor) {
		return String.valueOf(Math.round(valor * 100.0) / 100.0);
	}
	
	public boolean validarSiNumeroDecimal(String valor) {
		try {
			Double.valueOf(valor);
			return true;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
	}
}

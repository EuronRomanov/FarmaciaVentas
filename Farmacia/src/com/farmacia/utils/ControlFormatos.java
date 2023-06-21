package com.farmacia.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;

public class ControlFormatos {

	
	public boolean hayEspaciosVacios(String... espacios) {
		boolean flag=false;
		for (int i = 0; i < espacios.length; i++) {
			if (espacios[i].length()<1) {
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

	public Date cambiarLocaldateToDate(LocalDateTime fechaCaduca) {
		Instant insta=Timestamp.valueOf(fechaCaduca.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.S"))).toInstant();
		return  Date.from(insta);
	}
	
	public boolean validarFormatoFecha(String strDate) {
		if (strDate.trim().equals("")) {
			return false;
		} else {
               SimpleDateFormat sdfrmt=new SimpleDateFormat("yyyy-MM-dd");
               try {
				Date javaDate=sdfrmt.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
               return true;
		}
	}
}

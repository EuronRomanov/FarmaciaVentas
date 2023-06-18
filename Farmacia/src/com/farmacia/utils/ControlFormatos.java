package com.farmacia.utils;

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
}

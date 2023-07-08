package com.farmacia.entidades;

public final class SesionUsuario {
private static int codUsuario;
private static  String nombreUsuario;
public static int getCodUsuario() {
	return codUsuario;
}
public static String getNombreUsuario() {
	return nombreUsuario;
}
public static void setNombreUsuario(String nombreUsuario) {
	SesionUsuario.nombreUsuario = nombreUsuario;
}
public static void setCodUsuario(int codUsuario) {
	SesionUsuario.codUsuario = codUsuario;
}


}

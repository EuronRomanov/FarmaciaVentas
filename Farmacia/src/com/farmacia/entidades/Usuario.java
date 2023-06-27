package com.farmacia.entidades;


	public class Usuario {
	    private int codUsuario ;
	    private String  nombre ;
	    private String  password ;
	    private int estado ;
	    private int  administrador ;
	    private String  cedula;
		public Usuario() {
			super();
		}
		
		
		
		
		public Usuario(int codUsuario, String nombre, String password, int estado, int administrador, String cedula) {
			super();
			this.codUsuario = codUsuario;
			this.nombre = nombre;
			this.password = password;
			this.estado = estado;
			this.administrador = administrador;
			this.cedula = cedula;
		}




		public Usuario(String nombre, String password, int estado, int administrador, String cedula) {
			super();
			this.nombre = nombre;
			this.password = password;
			this.estado = estado;
			this.administrador = administrador;
			this.cedula = cedula;
		}
		
		


		public Usuario(int codUsuario, String nombre, int estado, int administrador, String cedula) {
			super();
			this.codUsuario = codUsuario;
			this.nombre = nombre;
			this.estado = estado;
			this.administrador = administrador;
			this.cedula = cedula;
		}




		public int getCodUsuario() {
			return codUsuario;
		}
		public void setCodUsuario(int codUsuario) {
			this.codUsuario = codUsuario;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getEstado() {
			return estado;
		}
		public void setEstado(int estado) {
			this.estado = estado;
		}
		public int getAdministrador() {
			return administrador;
		}
		public void setAdministrador(int administrador) {
			this.administrador = administrador;
		}




		public String getCedula() {
			return cedula;
		}




		public void setCedula(String cedula) {
			this.cedula = cedula;
		}
	    
	    
	    
	}


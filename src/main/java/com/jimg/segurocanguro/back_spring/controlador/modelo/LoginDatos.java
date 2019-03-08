package com.jimg.segurocanguro.back_spring.controlador.modelo;

import javax.validation.constraints.NotNull;


public class LoginDatos {
	
	@NotNull
	private String usuario;
	@NotNull
	private String clave;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

}

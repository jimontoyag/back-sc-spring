package com.jimg.segurocanguro.back_spring.controlador.modelo;

import java.util.List;

public class ListadoUsuarios {
	
	private List<String> usuarios;
	private String busqueda;
	
	public List<String> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}
	public String getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

}

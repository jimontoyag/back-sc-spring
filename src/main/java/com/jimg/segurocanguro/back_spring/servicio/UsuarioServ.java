package com.jimg.segurocanguro.back_spring.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServ {
	
	private Map<String,String> usuarios;
	
	@PostConstruct
	private void inicializar() {
		this.usuarios = new HashMap<String, String>(1);
		this.usuarios.put("segurocanguro", "159753");
	}
	
	public List<String> obtenerUsuarios(String usuario) {
		return usuarios.keySet().stream()
				.filter(usr -> usr.toLowerCase().contains(usuario))
				.collect(Collectors.toList());
	}
	
	public void crearUsuario(String usuario, String clave){
		this.usuarios.put(usuario, clave);
	}
	
	public boolean validaClave(String usuario, String clave) {
		return this.usuarios.get(usuario).equals(clave);
	}
}

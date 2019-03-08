package com.jimg.segurocanguro.back_spring.controlador;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimg.segurocanguro.back_spring.controlador.modelo.ListadoUsuarios;
import com.jimg.segurocanguro.back_spring.controlador.modelo.LoginDatos;
import com.jimg.segurocanguro.back_spring.controlador.modelo.TokenResp;
import com.jimg.segurocanguro.back_spring.error.LoginException;
import com.jimg.segurocanguro.back_spring.servicio.JwtServ;
import com.jimg.segurocanguro.back_spring.servicio.UsuarioServ;

@RestController
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServ usuarioServ;
	
	@Autowired
	private JwtServ jwtServ;
	
	@RequestMapping(value="/public/login", method=RequestMethod.POST)
	public TokenResp login(@RequestBody @Valid LoginDatos datos) {
		TokenResp resp = new TokenResp();
		if(!this.usuarioServ.validaClave(datos.getUsuario(), datos.getClave())) {
			resp.setSc_token("-1");
			resp.setMensaje("Error nombre de usuario o contraseña");
			throw new LoginException(resp);
		}else {
			resp.setSc_token(this.jwtServ.crearToken(datos.getUsuario()));
			resp.setMensaje("exito");
			return resp;
		}		
		
	}
	
	@RequestMapping(value="/usuario", method=RequestMethod.POST)
	public Map<String, String> crearUsuario(@RequestBody @Valid LoginDatos datos) {
		usuarioServ.crearUsuario(datos.getUsuario(), datos.getClave());
		return mensajeSimple("creado con exito "+datos.getUsuario());
	}
	
	@RequestMapping(value="/usuarios", method=RequestMethod.GET)
	public ListadoUsuarios usuarios(@RequestParam(defaultValue="") String busqueda){
		ListadoUsuarios rel = new ListadoUsuarios();
		rel.setUsuarios(usuarioServ.obtenerUsuarios(busqueda));
		rel.setBusqueda(busqueda);
		return rel;
	}
	
	
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public Map<String, String> saludar(String sc_token){
		return mensajeSimple("Hola "+jwtServ.usuarioToken(sc_token).getUsuario());
	}
	
	private Map<String, String> mensajeSimple(String mensaje){
		Map<String, String> rel = new HashMap<String, String>(1);
		rel.put("mensaje", mensaje);
		return rel;
	}

}

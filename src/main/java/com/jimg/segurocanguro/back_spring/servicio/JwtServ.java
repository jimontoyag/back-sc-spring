package com.jimg.segurocanguro.back_spring.servicio;

import java.security.Key;

import org.springframework.stereotype.Service;

import com.jimg.segurocanguro.back_spring.controlador.modelo.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServ {
	
	
	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	
	public String crearToken(String usuario) {
		return Jwts.builder()
			.setSubject(usuario)
				.signWith(this.key)
					.compact(); 
	}
	
	public boolean validarToken(String jwt) {
		try {
			Jwts.parser()
		    .setSigningKey(this.key) 
		    .parseClaimsJws(jwt);
		    return true;
		}   
		catch (JwtException ex) { 
		    return false;
		}
	}
	
	public Usuario usuarioToken(String jwt) {
		try {
			Jws<Claims> jws = Jwts.parser()
								.setSigningKey(this.key)
								.parseClaimsJws(jwt);
			Usuario usuario = new Usuario();
			usuario.setUsuario(jws.getBody().getSubject());
			return usuario;
		    
		}   
		catch (JwtException ex) { 
		    return null;
		}
	}

}

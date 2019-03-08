package com.jimg.segurocanguro.back_spring.seguridad;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jimg.segurocanguro.back_spring.servicio.JwtServ;

@Component
public class SeguridadFilter implements Filter {
	
	@Autowired
	private JwtServ jwtServ;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String URI = httpRequest.getRequestURI();
	    if(!URI.startsWith("/public")) {
	    	HttpServletResponse httpResponse = (HttpServletResponse) response;
	    	String token = httpRequest.getParameter("sc_token");
	    	if(token == null || !jwtServ.validarToken(token)) {
	    		httpResponse.sendError(401, "UNAUTHORIZED");
	    	} else {
	    		chain.doFilter(request, response);
	    	}
	    } else {
	    	chain.doFilter(request, response);
	    }
		
	}

}

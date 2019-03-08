package com.jimg.segurocanguro.back_spring.error;

import com.jimg.segurocanguro.back_spring.controlador.modelo.TokenResp;

@SuppressWarnings("serial")
public class LoginException extends RuntimeException {
	
	private TokenResp resp;
	
	public LoginException( TokenResp resp) {
		this.setResp(resp);
	}

	public TokenResp getResp() {
		return resp;
	}

	public void setResp(TokenResp resp) {
		this.resp = resp;
	}

}

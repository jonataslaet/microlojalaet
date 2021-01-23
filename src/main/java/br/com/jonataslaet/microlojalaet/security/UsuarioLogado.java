package br.com.jonataslaet.microlojalaet.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioLogado {
	
	public static UsuarioSS authenticated() {
		try {
			return (UsuarioSS)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}

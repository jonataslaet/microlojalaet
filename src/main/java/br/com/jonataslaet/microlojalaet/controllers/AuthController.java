package br.com.jonataslaet.microlojalaet.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.security.JWTUtil;
import br.com.jonataslaet.microlojalaet.security.UsuarioLogado;
import br.com.jonataslaet.microlojalaet.security.UsuarioSS;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

	@Autowired
	JWTUtil jwtUtil;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UsuarioSS user = UsuarioLogado.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer "+token);
		return ResponseEntity.noContent().build();
	}
}

package br.com.jonataslaet.microlojalaet.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.domain.Perfil;

public class UsuarioSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UsuarioSS(Cliente cli) {
		this.setId(cli.getId());
		this.email = cli.getEmail();
		this.senha = cli.getSenha();
		this.authorities = cli.getPerfis().stream().map(p -> new SimpleGrantedAuthority(p.getDescricao())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Perfil admin) {
		return getAuthorities().contains(new SimpleGrantedAuthority(admin.getDescricao()));
	}

}

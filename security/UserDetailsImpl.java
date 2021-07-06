package br.com.generation.personalBlog.security;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.generation.personalBlog.model.Usuario;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	
	public UserDetailsImpl (Usuario user) {
		this.usuario = user.getUsuario();
		this.senha = user.getSenha();
	}

	public UserDetailsImpl() {}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			return null;
	}
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return usuario;
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
}
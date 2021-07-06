package br.com.generation.personalBlog.security;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.generation.personalBlog.model.Usuario;
import br.com.generation.personalBlog.repository.UsuarioRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername (String usuario) throws UsernameNotFoundException {
		Optional<Usuario> user = repository.findByUsuario(usuario);
		user.orElseThrow(() -> new UsernameNotFoundException(usuario + " não encontrado."));
		return user.map(UserDetailsImpl::new).get();			
	}
}
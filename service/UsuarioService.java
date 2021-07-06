package br.com.generation.personalBlog.service;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/
import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.generation.personalBlog.model.Usuario;
import br.com.generation.personalBlog.model.userLogin;
import br.com.generation.personalBlog.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	
	public Optional<Usuario> inserirUsuario (Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return Optional.of(repository.save(usuario));
		
	}

	public Optional<Usuario> editarUsuario(Usuario usuario){
		
		if(repository.findById(usuario.getId()).isPresent()) {
					
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(repository.save(usuario));
		
		}else {
			
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!");
		}
	}
	
	public Optional<userLogin> Logar(Optional<userLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(user.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String (encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
		}
		return null;
	}
}
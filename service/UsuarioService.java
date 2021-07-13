package br.com.generation.personalBlog.service;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
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

	public Usuario cadastrarUsuario (Usuario usuario) {	
		
		if(repository.findByUsuario(usuario.getUsuario()).isPresent()) 
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário já existe!", null);
			
			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
			
			if(idade < 18)
				throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
	 	String senhaEncoder = encoder.encode(usuario.getSenha());//uso o MÉTODO get para extrair a senha do usuario e liberar o acesso
	 	usuario.setSenha(senhaEncoder);//o ENCODER criptografa a senha e "devolve" para o METODO set
	 	
	 	return (repository.save(usuario));
	}

	public Optional<Usuario> editarUsuario(Usuario usuario) {

		if (repository.findById(usuario.getId()).isPresent()) {

			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

			if (idade < 18)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);

			return Optional.of(repository.save(usuario));

		} else {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
		}
	}

	public Optional<userLogin> Logar(Optional<userLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

		if (user.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));// getBytes
																										// transforma
																										// cada
																										// caractere em
																										// binario e
																										// criptografa a
																										// senha do
																										// user.
				String authHeader = "Basic " + new String(encodedAuth);// isso aqui gera meu token
				// TEM q ser maiuscula e com espaco
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());

				return user;// aqui eu estou convocando TODAS as infos da minha classe userLogin. POREM eu
							// criei, na linha 51, um objeto user rs.
			}
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
	}
}
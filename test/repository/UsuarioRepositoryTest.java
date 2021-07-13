package br.com.generation.personalBlog.repository;
/*Função: Inserindo camada de teste no BlogPessoal
 * Autora: Talu - Turma 25
 * Data: 12.07.2021
*/
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.generation.personalBlog.model.Usuario;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	public void start() {
		Usuario usuario = new Usuario (0L, "Bruno Mars", "@brunomars", "brunomars@fakemail.com", "brun0-m@rs123");
		if (repository.findByEmail (usuario.getEmail()).isEmpty())
			repository.save(usuario);
		
		usuario = new Usuario(0L, "Eric Hernandez", "ericpanda", "hernandez@fakemail.com", "12345");
		if(repository.findByEmail(usuario.getEmail()).isEmpty())
			repository.save(usuario);
		
		usuario = new Usuario(0L, "Anderson .Paak", "imPAAKted", "go-andy@fakemail.com", "12345");
		if(repository.findByEmail(usuario.getEmail()).isEmpty())
			repository.save(usuario);
		
		usuario = new Usuario(0L, "Donald Glover", "childish-gambino", "gambino@fakemail.com", "54321");
		if(repository.findByEmail(usuario.getEmail()).isEmpty())
			repository.save(usuario);
	}
	@Test
	public void findByEmailRetornaUsuario() {
		Optional<Usuario> usuario = repository.findByEmail("");
		assertTrue(usuario.get().getEmail().equals(""));
	}
	@Test
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
		List<Usuario> usuarios = repository.findAllByNomeContainingIgnoreCase("Bruno");
		assertEquals (3, usuarios.size());
	}
	@Test
	public void findByNomeOrderByNomeRetornaUsuario() {
		Optional<Usuario> usuarioNome = repository.findFirstByNomeContainingIgnoreCaseOrderByNome("Paak");
		assertTrue(usuarioNome.get().getNome().equals("João da Silva")); //Esse,retorna o primeiro nome em ordem alfabética
	}
	@Test
	public void findByUsuarioRetornaUsuario() {
		Optional<Usuario> usuario = repository.findByUsuario("brunomars");
		assertTrue(usuario.get().getUsuario().equals("brunomars"));
	}
}
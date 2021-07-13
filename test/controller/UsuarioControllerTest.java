package br.com.generation.personalBlog.controller;
/*Função: Inserindo camada de teste no BlogPessoal
 * Autora: Talu - Turma 25
 * Data: 12.07.2021
*/
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.generation.personalBlog.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Usuario usuario;
	private Usuario usuarioupd;
	
	@BeforeAll
	public void start () {
		usuario = new Usuario(0L, "Bruno Mars", "@brunomars", "brunomars@fakemail.com", "brun0-m@rs123");
		usuarioupd = new Usuario(5L, "Anderson .Paak", "imPAAKted", "go-andy@fakemail.com", "12345");
	}
	
	@Disabled
	@Test
	public void novoUsuario () {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuario);
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void listarTodosUsuarios () {
		
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("mars-cinha", "12345").exchange("/usuario/todos", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void editarUsuario () {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioupd);
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("admin", "admin123").exchange("/usuario/alterar", HttpMethod.PUT, requisicao, Usuario.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
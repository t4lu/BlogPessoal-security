package br.com.generation.personalBlog.model;
/*Função: Inserindo camada de teste no BlogPessoal
 * Autora: Talu - Turma 25
 * Data: 12.07.2021
*/
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

	public Usuario usuario;
	public Usuario usuarioErro;
	
	@Autowired
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@BeforeEach
	public void start() {
	usuario = new Usuario ();
	usuarioErro = new Usuario();
	}
	
	@Test
	public void testValidationAtributos() {
		
		usuario.setNome("Anderson .Paak");
		usuario.setUsuario("andy-imPAAKted");
		usuario.setEmail("go-andy@fakemail.com");
		usuario.setSenha("12345");
		
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
	
		System.out.println(violations.toString());
		
		assertTrue(violations.isEmpty());
	}
	public void testValidationAtributosNull() {
		usuarioErro.setEmail("omgitsbrunomars@fakemail.com");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuarioErro);

		System.out.println(violations.toString());

		assertFalse(violations.isEmpty());

	}
}
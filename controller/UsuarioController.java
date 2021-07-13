package br.com.generation.personalBlog.controller;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.personalBlog.model.Usuario;
import br.com.generation.personalBlog.model.userLogin;
import br.com.generation.personalBlog.repository.UsuarioRepository;
import br.com.generation.personalBlog.service.UsuarioService;

@RestController
@RequestMapping ("/usuario")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping ("/listar")
	public List<Usuario> listar (){
		return repository.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());				
	}

	@PostMapping("/logar")
	public ResponseEntity<userLogin> logarUser(@RequestBody Optional<userLogin> usuario) {
		return service.Logar(usuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> newUsuario(@RequestBody Usuario usuario) {
		Usuario newUsuario = service.cadastrarUsuario(usuario);
		try {
				return ResponseEntity.ok(newUsuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
		Optional<Usuario> editarUser = service.editarUsuario(usuario);
		try {
			return ResponseEntity.ok(editarUser.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
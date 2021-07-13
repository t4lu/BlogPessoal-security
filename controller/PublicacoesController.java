package br.com.generation.personalBlog.controller;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.personalBlog.model.Publicacoes;
import br.com.generation.personalBlog.repository.PublicacoesRepository;
import br.com.generation.personalBlog.service.PublicacoesService;

@RestController
@RequestMapping ("/publicacoes")
@CrossOrigin ("*")
public class PublicacoesController {
	
	@Autowired
	private PublicacoesRepository repositorio;
	
	@Autowired
	private PublicacoesService service;
	
	@GetMapping ("/listar")
	public List<Publicacoes> listar(){
	return repositorio.findAll();
	}
	@GetMapping ("/{id}")
	public Publicacoes getById (@PathVariable long id) {
		return repositorio.findById(id);
	}
	@GetMapping ("/titulo/{titulo}")
	public List<Publicacoes> getByTitulo (@PathVariable String titulo) {
		return repositorio.findAllByTituloContainingIgnoreCase(titulo);
	}
	@PostMapping ("/new")
	public Publicacoes newPublicacao (@RequestBody Publicacoes publicacoes) {
		return repositorio.save(publicacoes);
	}
	@PutMapping ("/edit")
	public Publicacoes editPublicacao (@RequestBody Publicacoes publicacoes) {
		return repositorio.save(publicacoes);
	}
	@DeleteMapping ("/del/{id}")
	public Publicacoes deletePublicacao (@PathVariable long id) {
		return repositorio.deleteById(id);
	}
	@PutMapping ("/likes/{id}")
	public Publicacoes curtir (@PathVariable Long id ) {
		return service.curtir(id);
	}
	@PutMapping ("/dislikes/{id}")
	public Publicacoes descurtir (@PathVariable Long id ) {
		return service.descurtir(id);
	}
}
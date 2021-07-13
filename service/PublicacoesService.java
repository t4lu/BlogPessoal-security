package br.com.generation.personalBlog.service;
/*Função: "botão" likes e dislikes
 * Autora: Talu - Turma 25
 * Data: 13.07.2021
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.generation.personalBlog.model.Publicacoes;
import br.com.generation.personalBlog.repository.PublicacoesRepository;

@Service
public class PublicacoesService {

	@Autowired
	private PublicacoesRepository repository;
	
	private Publicacoes buscarPublicacaoPorId (Long id) {
		
		Publicacoes buscarPublicacaoPorId = repository.findById(id).orElse(null);
	
		if (buscarPublicacaoPorId != null) {
			
			return buscarPublicacaoPorId;
			
		} else {
			throw new EmptyResultDataAccessException(1);
		}
		
	}
	
	public Publicacoes curtir (Long id) {
		Publicacoes publicacoes = buscarPublicacaoPorId (id);
		
		publicacoes.setCurtidas(publicacoes.getCurtidas() + 1);
		
		return repository.save(publicacoes);
		
	}
	
	public Publicacoes descurtir (Long id) {
		Publicacoes publicacoes = buscarPublicacaoPorId (id);
		
		publicacoes.setCurtidas(publicacoes.getCurtidas() - 1);
		
		return repository.save(publicacoes);
	}
}

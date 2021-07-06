package br.com.generation.personalBlog.repository;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.generation.personalBlog.model.Assunto;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long>{

	public List<Assunto> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	public Assunto findById (long id);
	
	public Assunto deleteById (long id);
}
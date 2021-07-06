package br.com.generation.personalBlog.model;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_assunto" )
public class Assunto {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String descricao;
	
	
	@OneToMany (mappedBy = "assunto", cascade = CascadeType.ALL)
	@JsonIgnoreProperties ("assunto")
	private List<Publicacoes> publicacoes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Publicacoes> getPublicacoes() {
		return publicacoes;
	}
	public void setPublicacoes(List<Publicacoes> publicacoes) {
		this.publicacoes = publicacoes;
	}		
}
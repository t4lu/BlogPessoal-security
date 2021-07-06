package br.com.generation.personalBlog.model;
/*Função: Inserindo camada de seguranca no BlogPessoal (testagem no Postman)
 * Autora: Talu - Turma 25
 * Data: 06.07.2021
*/
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_posts")
public class Publicacoes {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) // equivalente ao "auto_increment" do MySQL.
	private long id;
	
	@NotNull (message = "Você precisa digitar um título")
	@Size (min = 5, max = 100)
	private String titulo;
	
	@NotNull
	@Size (min = 10, max = 1000)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.util.Date(System.currentTimeMillis());;
	
	@ManyToOne 
	@JsonIgnoreProperties ("publicacoes") //o comando manytoone vai criar a relacao do tema com os posts
	private Assunto assunto; //tema
	//Json evita os loopings da classe Assunto.
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Assunto getAssunto() {
		return assunto;
	}
	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}
}
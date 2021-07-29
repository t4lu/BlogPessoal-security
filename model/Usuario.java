package br.com.generation.personalBlog.model;
/*Função: Ajustes para o Front-end
 * Autora: Talu - Turma 25
 * Data: 29.07.2021
*/

package br.com.generation.personalBlog.model;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size (min = 2, max = 255)
	private String nome;
	
	@NotNull
	@Size (min = 2, max = 255)
	private String usuario;
	
	@Email
	@Size (min = 2, max = 100)
	private String email;
	
	@NotNull
	@Size (min = 5, max = 255)
	private String senha;
	
	
	private String foto;
	
	
	private String tipo;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List <Publicacoes> publicacoes;
	

	@Column(name = "dt_nascimento")
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataNascimento;
	
	public Usuario() {}
	
	public Usuario(long id, @Size(min = 2, max = 100) String nome, @NotNull @Size(min = 2, max = 100) String usuario,
			@Email 
			@Size(min = 5, max = 100)String email, @NotNull @Size(min = 5, max = 255) String senha, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Publicacoes> getPublicacoes() {
		return publicacoes;
	}
	public void setPublicacoes(List<Publicacoes> publicacoes) {
		this.publicacoes = publicacoes;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
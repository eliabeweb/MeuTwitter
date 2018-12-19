package com.meuTwitter.Twitter.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Integer id;
	
	
	@Column(unique = true)
	private String email;

	@Column
	private String senha;
	
	@Column
	private String telefone;
	
	@Column
	private String nomeDeUsuario;
	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Postagem> postagens = new ArrayList<>();

	public Usuario() {
	}
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void addPostagns(Postagem postagem) {
		this.postagens.add(postagem);
	}
	
	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
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

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
}

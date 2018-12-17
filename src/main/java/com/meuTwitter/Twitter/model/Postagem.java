package com.meuTwitter.Twitter.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Postagem {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String descricao;
	
	@Column
	private LocalDateTime horario;

	public Postagem() {

	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	

}

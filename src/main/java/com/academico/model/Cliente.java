package com.academico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CLIENTE")
@Entity(name="CLIENTE")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo NOME é obrigatório, por favor, informe um nome!")
	@Length(max = 45, message = "O nome deverá ter no máximo {max} caracteres")
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cpf;
	
	@NotBlank(message = "O campo TELEFONE é obrigatório, por favor, informe um nome!")
	@Column(nullable = false)
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private Agencia agencia;
	
	public Cliente(Long id, String nome, String cpf, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
		
	

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ContaCorrente> contacorrente = new ArrayList<>();

}
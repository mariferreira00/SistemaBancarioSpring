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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="AGENCIA")
@Entity(name="AGENCIA")
public class Agencia implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAgencia;

	@NotBlank(message = "O campo NOME é obrigatório, por favor, informe um nome!")
	@Column(nullable = false)
	private String nomeAgencia;

	private String endereco;

	private String telefone;
	
	
	@OneToMany(mappedBy = "agencia", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Cliente> cliente = new ArrayList<>();

}

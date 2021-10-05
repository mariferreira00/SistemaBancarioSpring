package com.academico.DTO;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperacaoDTO {

	private Long contaId;
	private int operacao;
	private BigDecimal valor = new BigDecimal(0);

	public OperacaoDTO(Long contaId, int operacao, BigDecimal valor) {
		super();
		this.contaId = contaId;
		this.operacao = operacao;
		this.valor = valor;
	}
	
}
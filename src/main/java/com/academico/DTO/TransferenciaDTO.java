package com.academico.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferenciaDTO {
	private Long idConta;
	private String contaDestino;
	private String agenciaDestino;
	private BigDecimal valor = new BigDecimal(0);
}

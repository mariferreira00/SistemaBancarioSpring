package com.academico.DTO;

import java.math.BigDecimal;

public class SaldoDTO {

	private BigDecimal valor = new BigDecimal(0);

	public BigDecimal getValor() {
		return valor;	
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
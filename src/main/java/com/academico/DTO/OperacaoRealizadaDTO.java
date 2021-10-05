package com.academico.DTO;

import java.math.BigDecimal;


public class OperacaoRealizadaDTO extends DetalhesDTO {


	private BigDecimal valor = new BigDecimal(0);
	
	private BigDecimal saldo = new BigDecimal(0);
	

	public OperacaoRealizadaDTO(int status, String msg, String date, BigDecimal valor, BigDecimal saldo) {
		super(status, msg, date);
		this.setValor(valor);
		this.setSaldo(saldo);
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public BigDecimal getSaldo() {
		return saldo;
	}


	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
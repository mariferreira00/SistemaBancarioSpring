package com.academico.model;

public enum OperacaoTipo {

	SAQUE(1, "Saque"),
	DEPOSITO (2, "Deposito"),
    TRANSFERENCIAENV(3, "Transferencia Efetuada"),
    TRANSFERENCIAREC(4, "Transferencia Recebida");
    
    private int cod;
    private String descricao;
    
    private OperacaoTipo(int cod, String descricao) {
    	this.cod = cod;
    	this.descricao = descricao;
    }
    
    public int getCod() {
    	return cod;
    }
    
    public String getDescricao() {
    	return descricao;
    }
    
    public static OperacaoTipo toEnum(Integer cod) {
    	
    	if (cod == null) {
    		return null;
    	}
    	
    	for (OperacaoTipo i : OperacaoTipo.values()) {
    		if (cod.equals(i.getCod())) {
    			return i;
    		}
    	}
    	
    	throw new IllegalArgumentException("O código informado é inválido");
    }
	
}
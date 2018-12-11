package com.test.financeiro.api.entities;

public enum Situacao {
	
	ABERTA("Aberta"),
	FECHADA("Fechada");
	
	private final String descricao;

	private Situacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	 

}

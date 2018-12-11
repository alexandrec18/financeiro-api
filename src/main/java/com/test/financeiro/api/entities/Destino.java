package com.test.financeiro.api.entities;

public enum Destino {

	N("Nacional"),
	I("Internacional");
	
	private final String descricao;

	private Destino(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
		
}

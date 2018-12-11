package com.test.financeiro.api.entities;

public enum FormaPagamento {

	DIN("Dinheiro"),
	CCF("Cartão de Crédito Fornecedor"),
	DPC("Depósito em Conta");
	
	private final String descricao;

	private FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

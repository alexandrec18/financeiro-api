package com.test.financeiro.api.entities;

public enum ProdutoVenda {
	
	DH("Diária de Hospedagem"),
	PA("Passagem Aérea"),
	PT("Pacote Turistico");
	
	private final String descricao;
	
	private ProdutoVenda(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

package com.test.financeiro.api.entities;

public enum ProdutoVenda {
	
	DH("Diária de Hospedagem"),
	PA("Passagem Aérea"),
	PT("Pacote Turistico"),
	
	AC("Aluguel de Carro"),
	VI("Visto"),
	SV("Seguro Viagem"),
	SE("Serviço"),
	IG("Ingresso"),
	CI("Chip Internacional");
	
	
	private final String descricao;
	
	private ProdutoVenda(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

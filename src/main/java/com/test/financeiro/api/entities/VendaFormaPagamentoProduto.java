package com.test.financeiro.api.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda_forma_pagamento_produto")
public class VendaFormaPagamentoProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_venda_forma_pagamento")
	private VendaFormaPagamento vendaFormaPagamento;
	
	@Enumerated(EnumType.STRING)
	private ProdutoVenda produto;
	
	private BigDecimal valor;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public VendaFormaPagamento getVendaFormaPagamento() {
		return vendaFormaPagamento;
	}

	public void setVendaFormaPagamento(VendaFormaPagamento vendaFormaPagamento) {
		this.vendaFormaPagamento = vendaFormaPagamento;
	}

	public ProdutoVenda getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVenda produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaFormaPagamentoProduto other = (VendaFormaPagamentoProduto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

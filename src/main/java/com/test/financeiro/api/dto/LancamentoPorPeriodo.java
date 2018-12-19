package com.test.financeiro.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.test.financeiro.api.entities.Pessoa;
import com.test.financeiro.api.entities.TipoLancamento;

public class LancamentoPorPeriodo {
	
    private TipoLancamento tipo;
	
	private Pessoa pessoa;
	
	private BigDecimal valor;
	
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;

	public LancamentoPorPeriodo(TipoLancamento tipo, Pessoa pessoa, BigDecimal valor, LocalDate dataVencimento,
			LocalDate dataPagamento) {
		this.tipo = tipo;
		this.pessoa = pessoa;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
}

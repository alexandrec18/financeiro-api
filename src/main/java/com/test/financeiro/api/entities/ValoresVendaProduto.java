package com.test.financeiro.api.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ValoresVendaProduto {

	@Column(name = "comissao_porcentagem")
	private BigDecimal comissaoPorcentagem;
	
	@Column(name = "comissao_valor")
	private BigDecimal comissaoValor;
	
	
	@Column(name = "over_porcentagem")
	private BigDecimal overPorcentagem;
	
	@Column(name = "over_sobre")
	private BigDecimal overSobre;
	
	@Column(name = "over_valor")
	private BigDecimal overValor;
	
	@ManyToOne
	@JoinColumn(name = "codigo_moeda")	
	private Moeda codigoMoeda;
	
	@Column(name = "cambio_valor")
	private BigDecimal cambioValor; 
	
	
	@Column(name = "operadora_abatimentos")
	private BigDecimal operadoraAbatimentos;
	
	@Column(name = "operadora_taxa_ccrav")
	private BigDecimal operadoraTaxaCcRav;	  
	
	
	@Column(name = "agencia_taxa_serv_destac")
	private BigDecimal agenciaTaxaServDestac;
	
	@Column(name = "agencia_desconto")
	private BigDecimal agenciaDesconto;
	
	@Column(name = "total_produto")
	private BigDecimal totalProduto;
	
	@Column(name = "total_produto_brl")
	private BigDecimal totalProdutoBrl;
	  
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@Column(name = "valor_total_brl")
	private BigDecimal valorTotalBrl;

	@Column(name = "saldo")
	private BigDecimal saldo;
	
	@Column(name = "saldo_brl")
	private BigDecimal saldoBrl;
	
	public BigDecimal getComissaoPorcentagem() {
		return comissaoPorcentagem;
	}

	public void setComissaoPorcentagem(BigDecimal comissaoPorcentagem) {
		this.comissaoPorcentagem = comissaoPorcentagem;
	}

	public BigDecimal getComissaoValor() {
		return comissaoValor;
	}

	public void setComissaoValor(BigDecimal comissaoValor) {
		this.comissaoValor = comissaoValor;
	}

	public BigDecimal getOverPorcentagem() {
		return overPorcentagem;
	}

	public void setOverPorcentagem(BigDecimal overPorcentagem) {
		this.overPorcentagem = overPorcentagem;
	}

	public BigDecimal getOverSobre() {
		return overSobre;
	}

	public void setOverSobre(BigDecimal overSobre) {
		this.overSobre = overSobre;
	}

	public BigDecimal getOverValor() {
		return overValor;
	}

	public void setOverValor(BigDecimal overValor) {
		this.overValor = overValor;
	}

	public Moeda getCodigoMoeda() {
		return codigoMoeda;
	}

	public void setCodigoMoeda(Moeda codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}

	public BigDecimal getCambioValor() {
		return cambioValor;
	}

	public void setCambioValor(BigDecimal cambioValor) {
		this.cambioValor = cambioValor;
	}

	public BigDecimal getOperadoraAbatimentos() {
		return operadoraAbatimentos;
	}

	public void setOperadoraAbatimentos(BigDecimal operadoraAbatimentos) {
		this.operadoraAbatimentos = operadoraAbatimentos;
	}

	public BigDecimal getOperadoraTaxaCcRav() {
		return operadoraTaxaCcRav;
	}

	public void setOperadoraTaxaCcRav(BigDecimal operadoraTaxaCcRav) {
		this.operadoraTaxaCcRav = operadoraTaxaCcRav;
	}

	public BigDecimal getAgenciaTaxaServDestac() {
		return agenciaTaxaServDestac;
	}

	public void setAgenciaTaxaServDestac(BigDecimal agenciaTaxaServDestac) {
		this.agenciaTaxaServDestac = agenciaTaxaServDestac;
	}

	public BigDecimal getAgenciaDesconto() {
		return agenciaDesconto;
	}

	public void setAgenciaDesconto(BigDecimal agenciaDesconto) {
		this.agenciaDesconto = agenciaDesconto;
	}

	public BigDecimal getTotalProduto() {
		return totalProduto;
	}

	public void setTotalProduto(BigDecimal totalProduto) {
		this.totalProduto = totalProduto;
	}

	public BigDecimal getTotalProdutoBrl() {
		return totalProdutoBrl;
	}

	public void setTotalProdutoBrl(BigDecimal totalProdutoBrl) {
		this.totalProdutoBrl = totalProdutoBrl;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotalBrl() {
		return valorTotalBrl;
	}

	public void setValorTotalBrl(BigDecimal valorTotalBrl) {
		this.valorTotalBrl = valorTotalBrl;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getSaldoBrl() {
		return saldoBrl;
	}

	public void setSaldoBrl(BigDecimal saldoBrl) {
		this.saldoBrl = saldoBrl;
	}
}

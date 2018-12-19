package com.test.financeiro.api.dto;

import java.time.LocalDate;

public class VendaPorDocumento {
	
	private LocalDate dataVenda;
	private String produto;
	private String formaPagamento;
	
	public VendaPorDocumento(LocalDate dataVenda, String produto, String formaPagamento) {
		this.dataVenda = dataVenda;
		this.produto = produto;
		this.formaPagamento = formaPagamento;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	
	
	
		
	// private Long codigo;
	
//	private LocalDate dataVenda;
//	
//	private Long numero;
//	
//	private Situacao situacao;
//	
//	private Empresa empresa;
//	
//	private Usuario vendedor;
//	
//	private Pessoa pagante;
//	
//	private LocalDate periodoInicial;
//	
//	private LocalDate periodoFinal;
//	
//	private Pessoa intermediario;
//	
//	private Pessoa solicitante;
//	
//	private String observacao;
//	
//	private List<VendaProduto> vendaProduto;
//	
//	private List<VendaFormaPagamento> vendaFormaPagamento;
//	
//	private BigDecimal totalProdutos;
//	
//	private BigDecimal totalFinal;
//
//	public VendaPorDocumento(LocalDate dataVenda, Long numero, Situacao situacao, Empresa empresa,
//			Usuario vendedor, Pessoa pagante, LocalDate periodoInicial, LocalDate periodoFinal, Pessoa intermediario,
//			Pessoa solicitante, String observacao, List<VendaProduto> vendaProduto,
//			List<VendaFormaPagamento> vendaFormaPagamento, BigDecimal totalProdutos, BigDecimal totalFinal) {
//		// this.codigo = codigo;
//		this.dataVenda = dataVenda;
//		this.numero = numero;
//		this.situacao = situacao;
//		this.empresa = empresa;
//		this.vendedor = vendedor;
//		this.pagante = pagante;
//		this.periodoInicial = periodoInicial;
//		this.periodoFinal = periodoFinal;
//		this.intermediario = intermediario;
//		this.solicitante = solicitante;
//		this.observacao = observacao;
//		this.vendaProduto = vendaProduto;
//		this.vendaFormaPagamento = vendaFormaPagamento;
//		this.totalProdutos = totalProdutos;
//		this.totalFinal = totalFinal;
//	}
//
////	public Long getCodigo() {
////		return codigo;
////	}
////
////	public void setCodigo(Long codigo) {
////		this.codigo = codigo;
////	}
//
//	public LocalDate getDataVenda() {
//		return dataVenda;
//	}
//
//	public void setDataVenda(LocalDate dataVenda) {
//		this.dataVenda = dataVenda;
//	}
//
//	public Long getNumero() {
//		return numero;
//	}
//
//	public void setNumero(Long numero) {
//		this.numero = numero;
//	}
//
//	public Situacao getSituacao() {
//		return situacao;
//	}
//
//	public void setSituacao(Situacao situacao) {
//		this.situacao = situacao;
//	}
//
//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}
//
//	public Usuario getVendedor() {
//		return vendedor;
//	}
//
//	public void setVendedor(Usuario vendedor) {
//		this.vendedor = vendedor;
//	}
//
//	public Pessoa getPagante() {
//		return pagante;
//	}
//
//	public void setPagante(Pessoa pagante) {
//		this.pagante = pagante;
//	}
//
//	public LocalDate getPeriodoInicial() {
//		return periodoInicial;
//	}
//
//	public void setPeriodoInicial(LocalDate periodoInicial) {
//		this.periodoInicial = periodoInicial;
//	}
//
//	public LocalDate getPeriodoFinal() {
//		return periodoFinal;
//	}
//
//	public void setPeriodoFinal(LocalDate periodoFinal) {
//		this.periodoFinal = periodoFinal;
//	}
//
//	public Pessoa getIntermediario() {
//		return intermediario;
//	}
//
//	public void setIntermediario(Pessoa intermediario) {
//		this.intermediario = intermediario;
//	}
//
//	public Pessoa getSolicitante() {
//		return solicitante;
//	}
//
//	public void setSolicitante(Pessoa solicitante) {
//		this.solicitante = solicitante;
//	}
//
//	public String getObservacao() {
//		return observacao;
//	}
//
//	public void setObservacao(String observacao) {
//		this.observacao = observacao;
//	}
//
//	public List<VendaProduto> getVendaProduto() {
//		return vendaProduto;
//	}
//
//	public void setVendaProduto(List<VendaProduto> vendaProduto) {
//		this.vendaProduto = vendaProduto;
//	}
//
//	public List<VendaFormaPagamento> getVendaFormaPagamento() {
//		return vendaFormaPagamento;
//	}
//
//	public void setVendaFormaPagamento(List<VendaFormaPagamento> vendaFormaPagamento) {
//		this.vendaFormaPagamento = vendaFormaPagamento;
//	}
//
//	public BigDecimal getTotalProdutos() {
//		return totalProdutos;
//	}
//
//	public void setTotalProdutos(BigDecimal totalProdutos) {
//		this.totalProdutos = totalProdutos;
//	}
//
//	public BigDecimal getTotalFinal() {
//		return totalFinal;
//	}
//
//	public void setTotalFinal(BigDecimal totalFinal) {
//		this.totalFinal = totalFinal;
//	}
	
}

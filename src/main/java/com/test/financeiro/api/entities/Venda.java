package com.test.financeiro.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "venda")
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Column(name = "data_venda")
	private LocalDate dataVenda;
	
	private Long numero;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	private Empresa empresa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_vendedor")
	private Usuario vendedor;
		
	@ManyToOne
	@JoinColumn(name = "codigo_pagante")
	private Pessoa pagante;
	
	@Column(name = "periodo_inicial")
	private LocalDate  periodoInicial;
	
	@Column(name = "periodo_final")
	private LocalDate  periodoFinal;	

	@ManyToOne
	@JoinColumn(name = "codigo_intermediario")
	private Pessoa intermediario;
	
	@ManyToOne
	@JoinColumn(name = "codigo_solicitante")
	private Pessoa solicitante;
	
	private String observacao;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@JsonIgnoreProperties("venda")
	@Valid
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, 
	  orphanRemoval = true)
	private List<VendaProduto> vendaProduto;
	
	@JsonIgnoreProperties("venda")
	@Valid
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, 
	  orphanRemoval = true)
	private List<VendaFormaPagamento> vendaFormaPagamento;
	
	@Column(name = "total_produtos")
	private BigDecimal totalProdutos;
	
	@Column(name = "total_final")
	private BigDecimal totalFinal;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Pessoa getPagante() {
		return pagante;
	}

	public void setPagante(Pessoa pagante) {
		this.pagante = pagante;
	}

	public LocalDate getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(LocalDate periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public LocalDate getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(LocalDate periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public Pessoa getIntermediario() {
		return intermediario;
	}

	public void setIntermediario(Pessoa intermediario) {
		this.intermediario = intermediario;
	}

	public Pessoa getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Pessoa solicitante) {
		this.solicitante = solicitante;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public List<VendaProduto> getVendaProduto() {
		return vendaProduto;
	}

	public void setVendaProduto(List<VendaProduto> vendaProduto) {
		this.vendaProduto = vendaProduto;
	}	

	public List<VendaFormaPagamento> getVendaFormaPagamento() {
		return vendaFormaPagamento;
	}

	public void setVendaFormaPagamento(List<VendaFormaPagamento> vendaFormaPagamento) {
		this.vendaFormaPagamento = vendaFormaPagamento;		
	}

	public BigDecimal getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(BigDecimal totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public BigDecimal getTotalFinal() {
		return totalFinal;
	}

	public void setTotalFinal(BigDecimal totalFinal) {
		this.totalFinal = totalFinal;
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
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

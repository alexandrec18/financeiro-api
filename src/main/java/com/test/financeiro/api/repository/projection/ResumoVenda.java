package com.test.financeiro.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.test.financeiro.api.entities.Situacao;

public class ResumoVenda {
	
	private Long codigo;
    private LocalDate dataVenda;
    private Situacao situacao;
    private String pagante;
    private Long numero;
    private BigDecimal totalProdutos;
    private BigDecimal totalFinal;
    
	public ResumoVenda(Long codigo, LocalDate dataVenda, Situacao situacao, String pagante,
			Long numero, BigDecimal totalProdutos, BigDecimal totalFinal) {
		this.codigo = codigo;
		this.dataVenda = dataVenda;
		this.situacao = situacao;
		this.pagante = pagante;
		this.numero = numero;
		this.totalProdutos = totalProdutos;
		this.totalFinal = totalFinal;
	}

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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getPagante() {
		return pagante;
	}

	public void setPagante(String pagante) {
		this.pagante = pagante;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
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

}

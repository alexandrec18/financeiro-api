package com.test.financeiro.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.test.financeiro.api.entities.Situacao;

public class VendaFilter {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate periodoInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate periodoFinal;
	
	private int empresa;
	
	private Situacao situacao;
	
	public String pagante;

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

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
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
	
}

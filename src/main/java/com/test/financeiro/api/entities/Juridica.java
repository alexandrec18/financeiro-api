package com.test.financeiro.api.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Juridica {
	
	@Column(name = "razao_social")
	private String razaoSocial;
	
	private String cnpj;
	
	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;
		
	private String website;
	
	private String telefone;

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	

}

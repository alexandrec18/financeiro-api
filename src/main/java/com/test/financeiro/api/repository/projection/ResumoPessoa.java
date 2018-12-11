package com.test.financeiro.api.repository.projection;

public class ResumoPessoa {
	
	private Long codigo;
	
	private String nome;
	
	private String cidade;
	
	private String estado;
	
	private Boolean ativo;

	public ResumoPessoa(Long codigo, String nome, String cidade, String estado, Boolean ativo) {
		this.codigo = codigo;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
		this.ativo = ativo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}

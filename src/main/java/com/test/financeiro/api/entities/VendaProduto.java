package com.test.financeiro.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "venda_produto")
public class VendaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_venda")
	private Venda venda;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private ProdutoVenda produto;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_fornecedor")
	private Pessoa fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "codigo_representante")
	private Pessoa representante;
	
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	
	@Column(name = "hora_inicio")
	private LocalDateTime horaInicio;
	
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@Column(name = "hora_fim")
	private LocalDateTime horaFim;
	
	@ManyToOne
	@JoinColumn(name = "codigo_acomodacao")
	private Acomodacao acomodacao;

	@ManyToOne
	@JoinColumn(name = "codigo_tipo_acomodacao")
	private TipoAcomodacao tipoAcomodacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_regime")
	private Regime regime;
	
	@ManyToOne
	@JoinColumn(name = "codigo_pacote")
	private Pacote pacote;
	
	@ManyToOne
	@JoinColumn(name = "codigo_transporte")
	private Transporte transporte;
	
	@JoinColumn(name = "servicos_inclusos")
	private String servicosInclusos;
	  
	private String documento;
	
	@Enumerated(EnumType.STRING)
	private Destino destino;
	
	@JsonIgnoreProperties("vendaProduto")
	@Valid
	@OneToMany(mappedBy = "vendaProduto", cascade = CascadeType.ALL, 
	  orphanRemoval = true)
	private List<VendaProdutoPassageiro> vendaProdutoPassageiro;
	
	@JsonIgnoreProperties("vendaProduto")
	@Valid
	@OneToMany(mappedBy = "vendaProduto", cascade = CascadeType.ALL, 
	  orphanRemoval = true)
	private List<VendaProdutoTrecho> vendaProdutoTrecho;
	
	@Embedded
	private ValoresVendaProduto valoresVendaProduto;
	  
	@Column(name = "numero_nf")
	private String numeroNf;
	
	@Column(name = "numero_externo")
	private String numeroExterno;
	
	@Column(name = "recibo_operadora")
	private String reciboOperadora;
	  
	private String observacao;
	
	private String retirada;
	
	private String devolucao;
	
	private String categoria;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public ProdutoVenda getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVenda produto) {
		this.produto = produto;
	}

	public Pessoa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Pessoa fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Pessoa getRepresentante() {
		return representante;
	}

	public void setRepresentante(Pessoa representante) {
		this.representante = representante;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalDateTime horaFim) {
		this.horaFim = horaFim;
	}

	public Acomodacao getAcomodacao() {
		return acomodacao;
	}

	public void setAcomodacao(Acomodacao acomodacao) {
		this.acomodacao = acomodacao;
	}

	public TipoAcomodacao getTipoAcomodacao() {
		return tipoAcomodacao;
	}

	public void setTipoAcomodacao(TipoAcomodacao tipoAcomodacao) {
		this.tipoAcomodacao = tipoAcomodacao;
	}

	public Regime getRegime() {
		return regime;
	}

	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public Transporte getTransporte() {
		return transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	public String getServicosInclusos() {
		return servicosInclusos;
	}

	public void setServicosInclusos(String servicos_inclusos) {
		this.servicosInclusos = servicos_inclusos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public List<VendaProdutoPassageiro> getVendaProdutoPassageiro() {
		return vendaProdutoPassageiro;
	}

	public void setVendaProdutoPassageiro(List<VendaProdutoPassageiro> vendaProdutoPassageiro) {
		this.vendaProdutoPassageiro = vendaProdutoPassageiro;
	}

	public List<VendaProdutoTrecho> getVendaProdutoTrecho() {
		return vendaProdutoTrecho;
	}

	public void setVendaProdutoTrecho(List<VendaProdutoTrecho> vendaProdutoTrecho) {
		this.vendaProdutoTrecho = vendaProdutoTrecho;
	}

	public ValoresVendaProduto getValoresVendaProduto() {
		return valoresVendaProduto;
	}

	public void setValoresVendaProduto(ValoresVendaProduto valoresVendaProduto) {
		this.valoresVendaProduto = valoresVendaProduto;
	}

	public String getNumeroNf() {
		return numeroNf;
	}

	public void setNumeroNf(String numeroNf) {
		this.numeroNf = numeroNf;
	}

	public String getNumeroExterno() {
		return numeroExterno;
	}

	public void setNumeroExterno(String numeroExterno) {
		this.numeroExterno = numeroExterno;
	}

	public String getReciboOperadora() {
		return reciboOperadora;
	}

	public void setReciboOperadora(String reciboOperadora) {
		this.reciboOperadora = reciboOperadora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getRetirada() {
		return retirada;
	}

	public void setRetirada(String retirada) {
		this.retirada = retirada;
	}

	public String getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(String devolucao) {
		this.devolucao = devolucao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
		VendaProduto other = (VendaProduto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}

package com.test.financeiro.api.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda_produto_passageiro")
public class VendaProdutoPassageiro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_venda_produto")
	private VendaProduto vendaProduto;
	
	@ManyToOne
	@JoinColumn(name = "codigo_passageiro")
	private Pessoa passageiro;
	
	@Column(name = "nome_emissao")
	private String nomeEmissao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_moeda_origem")
	private Moeda moedaOrigem;
	
	@Column(name = "cambio_valor")
	private BigDecimal cambioValor;
	
	@Column(name = "valor_produto")
	private BigDecimal valorProduto;
	
	@Column(name = "valor_produto_brl")
	private BigDecimal valorProdutoBrl;
		
	private BigDecimal taxas;
	
	@Column(name = "taxas_brl")
	private BigDecimal taxasBrl;
	
	@Column(name = "outras_taxas")
	private BigDecimal outrasTaxas;
	
	@Column(name = "outras_taxas_brl")
	private BigDecimal outrasTaxasBrl;
	
	@Column(name = "taxa_rav")
	private BigDecimal taxaRav;
	
	@Column(name = "taxa_rav_brl")
	private BigDecimal taxaRavBrl;
	
	@Column(name = "taxa_du")
	private BigDecimal taxaDu;
	
	@Column(name = "taxa_du_brl")
	private BigDecimal taxaDuBrl;
	
	@Column(name = "taxa_servico_oculta")
	private BigDecimal taxaServicoOculta;
	
	@Column(name = "taxa_servico_oculta_brl")
	private BigDecimal taxaServicoOcultaBrl;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@Column(name = "valor_total_brl")
	private BigDecimal valorTotalBrl;
	
	@Column(name = "centro_custo")
	private String centroCusto;
	
	private String documento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public VendaProduto getVendaProduto() {
		return vendaProduto;
	}

	public void setVendaProduto(VendaProduto vendaProduto) {
		this.vendaProduto = vendaProduto;
	}

	public Pessoa getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Pessoa passageiro) {
		this.passageiro = passageiro;
	}

	public String getNomeEmissao() {
		return nomeEmissao;
	}

	public void setNomeEmissao(String nomeEmissao) {
		this.nomeEmissao = nomeEmissao;
	}

	public Moeda getMoedaOrigem() {
		return moedaOrigem;
	}

	public void setMoedaOrigem(Moeda moedaOrigem) {
		this.moedaOrigem = moedaOrigem;
	}

	public BigDecimal getCambioValor() {
		return cambioValor;
	}

	public void setCambioValor(BigDecimal cambioValor) {
		this.cambioValor = cambioValor;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}

	public BigDecimal getValorProdutoBrl() {
		return valorProdutoBrl;
	}

	public void setValorProdutoBrl(BigDecimal valorProdutoBrl) {
		this.valorProdutoBrl = valorProdutoBrl;
	}

	public BigDecimal getTaxas() {
		return taxas;
	}

	public void setTaxas(BigDecimal taxas) {
		this.taxas = taxas;
	}

	public BigDecimal getTaxasBrl() {
		return taxasBrl;
	}

	public void setTaxasBrl(BigDecimal taxasBrl) {
		this.taxasBrl = taxasBrl;
	}

	public BigDecimal getOutrasTaxas() {
		return outrasTaxas;
	}

	public void setOutrasTaxas(BigDecimal outrasTaxas) {
		this.outrasTaxas = outrasTaxas;
	}

	public BigDecimal getOutrasTaxasBrl() {
		return outrasTaxasBrl;
	}

	public void setOutrasTaxasBrl(BigDecimal outrasTaxasBrl) {
		this.outrasTaxasBrl = outrasTaxasBrl;
	}

	public BigDecimal getTaxaRav() {
		return taxaRav;
	}

	public void setTaxaRav(BigDecimal taxaRav) {
		this.taxaRav = taxaRav;
	}

	public BigDecimal getTaxaRavBrl() {
		return taxaRavBrl;
	}

	public void setTaxaRavBrl(BigDecimal taxaRavBrl) {
		this.taxaRavBrl = taxaRavBrl;
	}

	public BigDecimal getTaxaDu() {
		return taxaDu;
	}

	public void setTaxaDu(BigDecimal taxaDu) {
		this.taxaDu = taxaDu;
	}

	public BigDecimal getTaxaDuBrl() {
		return taxaDuBrl;
	}

	public void setTaxaDuBrl(BigDecimal taxaDuBrl) {
		this.taxaDuBrl = taxaDuBrl;
	}

	public BigDecimal getTaxaServicoOculta() {
		return taxaServicoOculta;
	}

	public void setTaxaServicoOculta(BigDecimal taxaServicoOculta) {
		this.taxaServicoOculta = taxaServicoOculta;
	}

	public BigDecimal getTaxaServicoOcultaBrl() {
		return taxaServicoOcultaBrl;
	}

	public void setTaxaServicoOcultaBrl(BigDecimal taxaServicoOcultaBrl) {
		this.taxaServicoOcultaBrl = taxaServicoOcultaBrl;
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

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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
		VendaProdutoPassageiro other = (VendaProdutoPassageiro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

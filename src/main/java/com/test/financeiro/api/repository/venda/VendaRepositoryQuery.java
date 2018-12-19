package com.test.financeiro.api.repository.venda;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.dto.VendaPorDocumento;
import com.test.financeiro.api.entities.Venda;
import com.test.financeiro.api.repository.filter.VendaFilter;
import com.test.financeiro.api.repository.projection.ResumoVenda;

public interface VendaRepositoryQuery {
	
	public List<VendaPorDocumento> porDocumento(Long codigo);
	public Page<Venda> filtrar(VendaFilter vendaFilter, Pageable pageable);
	public Page<ResumoVenda> resumir(VendaFilter vendaFilter, Pageable pageable);	

}

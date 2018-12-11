package com.test.financeiro.api.repository.tipoacomodacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.TipoAcomodacao;
import com.test.financeiro.api.repository.filter.TipoAcomodacaoFilter;

public interface TipoAcomodacaoRepositoryQuery {
	
	public Page<TipoAcomodacao> filtrar(TipoAcomodacaoFilter tipoAcomodacaoFilter, Pageable pageable);

}

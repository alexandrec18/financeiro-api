package com.test.financeiro.api.repository.acomodacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Acomodacao;
import com.test.financeiro.api.repository.filter.AcomodacaoFilter;

public interface AcomodacaoRepositoryQuery {

	public Page<Acomodacao> filtrar(AcomodacaoFilter acomodacaoFilter, Pageable pageable);
}

package com.test.financeiro.api.repository.moeda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Moeda;
import com.test.financeiro.api.repository.filter.MoedaFilter;

public interface MoedaRepositoryQuery {
	
	public Page<Moeda> filtrar(MoedaFilter moedaFilter, Pageable pageable);

}

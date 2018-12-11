package com.test.financeiro.api.repository.pacote;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Pacote;
import com.test.financeiro.api.repository.filter.PacoteFilter;

public interface PacoteRepositoryQuery {

	public Page<Pacote> filtrar(PacoteFilter pacoteFilter, Pageable pageable);
	
}

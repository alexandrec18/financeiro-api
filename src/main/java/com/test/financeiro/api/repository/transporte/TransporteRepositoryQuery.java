package com.test.financeiro.api.repository.transporte;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Transporte;
import com.test.financeiro.api.repository.filter.TransporteFilter;

public interface TransporteRepositoryQuery {

	public Page<Transporte> filtrar(TransporteFilter transporteFilter, Pageable pageable);
}

package com.test.financeiro.api.repository.regime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Regime;
import com.test.financeiro.api.repository.filter.RegimeFilter;

public interface RegimeRepositoryQuery {

	public Page<Regime> filtrar(RegimeFilter regimeFilter, Pageable pageable);
}

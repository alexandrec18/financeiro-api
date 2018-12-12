package com.test.financeiro.api.repository.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Empresa;
import com.test.financeiro.api.repository.filter.EmpresaFilter;

public interface EmpresaRepositoryQuery {

	public Page<Empresa> filtrar(EmpresaFilter empresaFilter, Pageable pageable);
	
}

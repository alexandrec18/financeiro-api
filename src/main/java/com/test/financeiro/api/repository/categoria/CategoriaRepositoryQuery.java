package com.test.financeiro.api.repository.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Categoria;
import com.test.financeiro.api.repository.filter.CategoriaFilter;

public interface CategoriaRepositoryQuery {
	
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable);

}

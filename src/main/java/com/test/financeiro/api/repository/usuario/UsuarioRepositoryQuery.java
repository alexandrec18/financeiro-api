package com.test.financeiro.api.repository.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Usuario;
import com.test.financeiro.api.repository.filter.UsuarioFilter;

public interface UsuarioRepositoryQuery {
	
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

}

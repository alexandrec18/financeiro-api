package com.test.financeiro.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Usuario;
import com.test.financeiro.api.repository.usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{
	
	public Optional<Usuario> findByEmail(String email);
	
	//Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
	
	public List<Usuario> findByPermissoesDescricao(String descricao);

}

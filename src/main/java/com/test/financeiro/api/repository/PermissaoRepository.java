package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}

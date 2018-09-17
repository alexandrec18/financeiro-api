package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	

}

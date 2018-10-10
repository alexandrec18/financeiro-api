package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Pessoa;
import com.test.financeiro.api.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{
	
	//Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
	

}

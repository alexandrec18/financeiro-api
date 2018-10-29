package com.test.financeiro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	List<Cidade> findByEstadoCodigo(Long estadoCodigo);

}

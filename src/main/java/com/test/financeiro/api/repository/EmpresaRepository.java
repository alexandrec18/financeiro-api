package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Empresa;
import com.test.financeiro.api.repository.empresa.EmpresaRepositoryQuery;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, EmpresaRepositoryQuery{
	
	//Page<Empresa> findByNomeContaining(String nome, Pageable pageable);

}

package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Empresa;
import com.test.financeiro.api.entities.VendaNumero;

public interface VendaNumeroRepository extends JpaRepository<VendaNumero, Long>{
	
	VendaNumero findByEmpresa(Empresa empresa);

}

package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Transporte;
import com.test.financeiro.api.repository.transporte.TransporteRepositoryQuery;

public interface TransporteRepository extends JpaRepository<Transporte, Long>, TransporteRepositoryQuery {

}

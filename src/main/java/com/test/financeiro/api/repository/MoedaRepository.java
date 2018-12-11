package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Moeda;
import com.test.financeiro.api.repository.moeda.MoedaRepositoryQuery;

public interface MoedaRepository extends JpaRepository<Moeda, Long>, MoedaRepositoryQuery {

}

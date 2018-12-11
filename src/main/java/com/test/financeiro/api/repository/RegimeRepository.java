package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Regime;
import com.test.financeiro.api.repository.regime.RegimeRepositoryQuery;

public interface RegimeRepository extends JpaRepository<Regime, Long>, RegimeRepositoryQuery {

}

package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Acomodacao;
import com.test.financeiro.api.repository.acomodacao.AcomodacaoRepositoryQuery;

public interface AcomodacaoRepository extends JpaRepository<Acomodacao, Long>, AcomodacaoRepositoryQuery{

}

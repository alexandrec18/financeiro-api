package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.TipoAcomodacao;
import com.test.financeiro.api.repository.tipoacomodacao.TipoAcomodacaoRepositoryQuery;

public interface TipoAcomodacaoRepository extends JpaRepository<TipoAcomodacao, Long>, TipoAcomodacaoRepositoryQuery {

}

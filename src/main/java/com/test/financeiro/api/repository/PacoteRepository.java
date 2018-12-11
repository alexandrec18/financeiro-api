package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Pacote;
import com.test.financeiro.api.repository.pacote.PacoteRepositoryQuery;

public interface PacoteRepository extends JpaRepository<Pacote, Long>, PacoteRepositoryQuery {

}

package com.test.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.financeiro.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{	

}

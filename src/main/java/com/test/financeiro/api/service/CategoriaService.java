package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Categoria;
import com.test.financeiro.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = buscarCategoriaExistente(codigo);				
		
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return categoriaRepository.save(categoriaSalva);		
	}
	
	private Categoria buscarCategoriaExistente(Long codigo) {
		Optional<Categoria> categoriaSalva = categoriaRepository.findById(codigo);
		
		if (!categoriaSalva.isPresent()) {
			throw new IllegalArgumentException();
		}
		return categoriaSalva.get();
	}
}

package com.test.financeiro.api.service;

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
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		
		if (categoriaSalva == null) {
			throw new IllegalArgumentException();
		}
		return categoriaSalva;
	}
}

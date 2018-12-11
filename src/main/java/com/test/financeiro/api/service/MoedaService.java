package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Moeda;
import com.test.financeiro.api.repository.MoedaRepository;

@Service
public class MoedaService {

	@Autowired
	private MoedaRepository moedaRepository;

	public Moeda atualizar(Long codigo, Moeda moeda) {
		Moeda moedaSalva = buscarMoedaExistente(codigo);				
		
		BeanUtils.copyProperties(moeda, moedaSalva, "codigo");
		return moedaRepository.save(moedaSalva);		
	}
	
	private Moeda buscarMoedaExistente(Long codigo) {
		Optional<Moeda> moedaSalva = moedaRepository.findById(codigo);
		
		if (!moedaSalva.isPresent()) {
			throw new IllegalArgumentException();
		}
		return moedaSalva.get();
	}
	
}

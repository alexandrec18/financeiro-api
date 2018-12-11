package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Pacote;
import com.test.financeiro.api.repository.PacoteRepository;

@Service
public class PacoteService {

	@Autowired
	private PacoteRepository pacoteRepository;

	public Pacote atualizar(Long codigo, Pacote categoria) {
		Pacote pacoteSalvo = buscarPacoteExistente(codigo);				
		
		BeanUtils.copyProperties(categoria, pacoteSalvo, "codigo");
		return pacoteRepository.save(pacoteSalvo);		
	}
	
	private Pacote buscarPacoteExistente(Long codigo) {
		Optional<Pacote> pacoteSalvo = pacoteRepository.findById(codigo);
		
		if (!pacoteSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return pacoteSalvo.get();
	}
}

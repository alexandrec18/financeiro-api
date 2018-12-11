package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Acomodacao;
import com.test.financeiro.api.repository.AcomodacaoRepository;

@Service
public class AcomodacaoService {

	@Autowired
	private AcomodacaoRepository acomodacaoRepository;

	public Acomodacao atualizar(Long codigo, Acomodacao acomodacao) {
		Acomodacao acomodacaoSalva = buscarAcomodacaoExistente(codigo);				
		
		BeanUtils.copyProperties(acomodacao, acomodacaoSalva, "codigo");
		return acomodacaoRepository.save(acomodacaoSalva);		
	}
	
	private Acomodacao buscarAcomodacaoExistente(Long codigo) {
		Optional<Acomodacao> acomodacaoSalva = acomodacaoRepository.findById(codigo);
		
		if (!acomodacaoSalva.isPresent()) {
			throw new IllegalArgumentException();
		}
		return acomodacaoSalva.get();
	}
}

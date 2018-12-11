package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.TipoAcomodacao;
import com.test.financeiro.api.repository.TipoAcomodacaoRepository;

@Service
public class TipoAcomodacaoService {

	@Autowired
	private TipoAcomodacaoRepository tipoAcomodacaoRepository;

	public TipoAcomodacao atualizar(Long codigo, TipoAcomodacao tipoAcomodacao) {
		TipoAcomodacao tipoAcomodacaoSalva = buscarTipoAcomodacaoExistente(codigo);				
		
		BeanUtils.copyProperties(tipoAcomodacao, tipoAcomodacaoSalva, "codigo");
		return tipoAcomodacaoRepository.save(tipoAcomodacaoSalva);		
	}
	
	private TipoAcomodacao buscarTipoAcomodacaoExistente(Long codigo) {
		Optional<TipoAcomodacao> tipoAcomodacaoSalva = tipoAcomodacaoRepository.findById(codigo);
		
		if (!tipoAcomodacaoSalva.isPresent()) {
			throw new IllegalArgumentException();
		}
		return tipoAcomodacaoSalva.get();
	}
}

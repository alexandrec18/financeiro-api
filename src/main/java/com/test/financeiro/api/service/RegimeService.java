package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Regime;
import com.test.financeiro.api.repository.RegimeRepository;

@Service
public class RegimeService {

	@Autowired
	private RegimeRepository regimeRepository;

	public Regime atualizar(Long codigo, Regime regime) {
		Regime regimeSalvo = buscarRegimeExistente(codigo);				
		
		BeanUtils.copyProperties(regime, regimeSalvo, "codigo");
		return regimeRepository.save(regimeSalvo);		
	}
	
	private Regime buscarRegimeExistente(Long codigo) {
		Optional<Regime> regimeSalvo = regimeRepository.findById(codigo);
		
		if (!regimeSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return regimeSalvo.get();
	}
}

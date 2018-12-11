package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Transporte;
import com.test.financeiro.api.repository.TransporteRepository;

@Service
public class TransporteService {

	@Autowired
	private TransporteRepository transporteRepository;

	public Transporte atualizar(Long codigo, Transporte transporte) {
		Transporte transporteSalvo = buscarTransporteExistente(codigo);				
		
		BeanUtils.copyProperties(transporte, transporteSalvo, "codigo");
		return transporteRepository.save(transporteSalvo);		
	}
	
	private Transporte buscarTransporteExistente(Long codigo) {
		Optional<Transporte> transporteSalvo = transporteRepository.findById(codigo);
		
		if (!transporteSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return transporteSalvo.get();
	}
}

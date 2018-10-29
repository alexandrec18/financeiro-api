package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Empresa;
import com.test.financeiro.api.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Empresa atualizar(Long codigo, Empresa empresa) {
		Empresa empresaSalva = buscarEmpresaPeloCodigo(codigo);
		
		BeanUtils.copyProperties(empresa, empresaSalva, "codigo");
		return empresaRepository.save(empresaSalva);		
	}		

	public Empresa buscarEmpresaPeloCodigo(Long codigo) {
		Optional<Empresa> empresaSalva = empresaRepository.findById(codigo);
		
		if (!empresaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return empresaSalva.get()	;
	}	

}

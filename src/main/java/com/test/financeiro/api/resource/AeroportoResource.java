package com.test.financeiro.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.financeiro.api.entities.Aeroporto;
import com.test.financeiro.api.repository.AeroportoRepository;

@RestController
@RequestMapping("/aeroportos")
public class AeroportoResource {

	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public List<Aeroporto> listar() {
		return aeroportoRepository.findAll();
	}
}

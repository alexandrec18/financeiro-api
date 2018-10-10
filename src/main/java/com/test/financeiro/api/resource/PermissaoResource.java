package com.test.financeiro.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.financeiro.api.entities.Permissao;
import com.test.financeiro.api.repository.PermissaoRepository;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERMISSAO') and #oauth2.hasScope('read')")
	public List<Permissao> listar(){
		return permissaoRepository.findAll();
	}

}

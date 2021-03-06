package com.test.financeiro.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.financeiro.api.entities.Empresa;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.repository.EmpresaRepository;
import com.test.financeiro.api.repository.filter.EmpresaFilter;
import com.test.financeiro.api.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EMPRESA') and #oauth2.hasScope('read')")
	public Page<Empresa> listar(EmpresaFilter empresaFilter, Pageable pageable){
		return empresaRepository.filtrar(empresaFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA') and #oauth2.hasScope('write')")
	public ResponseEntity<Empresa> criar(@Valid @RequestBody Empresa empresa, HttpServletResponse response) {
		
		Empresa empresaSalva = empresaRepository.save(empresa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, empresaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalva);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EMPRESA') and #oauth2.hasScope('read')")
	public ResponseEntity<Empresa> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Empresa> empresa =  empresaRepository.findById(codigo);
		return empresa.isPresent() ? ResponseEntity.ok(empresa.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_EMPRESA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		empresaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA') and #oauth2.hasScope('write')")
	public ResponseEntity<Empresa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Empresa empresa){
		
		Empresa empresaSalva = empresaService.atualizar(codigo, empresa);
		
		return ResponseEntity.ok(empresaSalva);
	}

}

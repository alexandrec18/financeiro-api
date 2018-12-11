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

import com.test.financeiro.api.entities.Transporte;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.repository.TransporteRepository;
import com.test.financeiro.api.repository.filter.TransporteFilter;
import com.test.financeiro.api.service.TransporteService;

@RestController
@RequestMapping("/transportes")
public class TransporteResource {

	@Autowired
	private TransporteRepository transporteRepository;
	
	@Autowired
	private TransporteService transporteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TRANSPORTE') and #oauth2.hasScope('read')")		
	public Page<Transporte> pesquisar(TransporteFilter transporteFilter, Pageable pageable){
		return transporteRepository.filtrar(transporteFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TRANSPORTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Transporte> criar(@Valid @RequestBody Transporte transporte, HttpServletResponse response) {
		
		Transporte transporteSalva = transporteRepository.save(transporte);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, transporteSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transporteSalva);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TRANSPORTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Transporte> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Transporte> transporte =  transporteRepository.findById(codigo);
		return transporte.isPresent() ? ResponseEntity.ok(transporte.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_TRANSPORTE') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		transporteRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TRANSPORTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Transporte> atualizar(@PathVariable Long codigo, @Valid @RequestBody Transporte transporte){
		try {
			Transporte transporteSalva = transporteService.atualizar(codigo, transporte);			
			return ResponseEntity.ok(transporteSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}	

}

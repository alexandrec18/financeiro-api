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

import com.test.financeiro.api.entities.Pacote;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.repository.PacoteRepository;
import com.test.financeiro.api.repository.filter.PacoteFilter;
import com.test.financeiro.api.service.PacoteService;

@RestController
@RequestMapping("/pacotes")
public class PacoteResource {

	@Autowired
	private PacoteRepository pacoteRepository;
	
	@Autowired
	private PacoteService pacoteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PACOTE') and #oauth2.hasScope('read')")		
	public Page<Pacote> pesquisar(PacoteFilter pacoteFilter, Pageable pageable){
		return pacoteRepository.filtrar(pacoteFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PACOTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Pacote> criar(@Valid @RequestBody Pacote pacote, HttpServletResponse response) {
		
		Pacote pacoteSalva = pacoteRepository.save(pacote);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pacoteSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pacoteSalva);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PACOTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Pacote> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Pacote> pacote =  pacoteRepository.findById(codigo);
		return pacote.isPresent() ? ResponseEntity.ok(pacote.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PACOTE') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		pacoteRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PACOTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Pacote> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pacote pacote){
		try {
			Pacote pacoteSalva = pacoteService.atualizar(codigo, pacote);			
			return ResponseEntity.ok(pacoteSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}	

}

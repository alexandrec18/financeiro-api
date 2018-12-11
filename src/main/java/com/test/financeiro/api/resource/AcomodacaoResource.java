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

import com.test.financeiro.api.entities.Acomodacao;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.repository.AcomodacaoRepository;
import com.test.financeiro.api.repository.filter.AcomodacaoFilter;
import com.test.financeiro.api.service.AcomodacaoService;

@RestController
@RequestMapping("/acomodacoes")
public class AcomodacaoResource {

	@Autowired
	private AcomodacaoRepository acomodacaoRepository;
	
	@Autowired
	private AcomodacaoService acomodacaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ACOMODACAO') and #oauth2.hasScope('read')")		
	public Page<Acomodacao> pesquisar(AcomodacaoFilter acomodacaoFilter, Pageable pageable){
		return acomodacaoRepository.filtrar(acomodacaoFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ACOMODACAO') and #oauth2.hasScope('write')")
	public ResponseEntity<Acomodacao> criar(@Valid @RequestBody Acomodacao acomodacao, HttpServletResponse response) {
		
		Acomodacao acomodacaoSalva = acomodacaoRepository.save(acomodacao);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, acomodacaoSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(acomodacaoSalva);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ACOMODACAO') and #oauth2.hasScope('read')")
	public ResponseEntity<Acomodacao> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Acomodacao> acomodacao =  acomodacaoRepository.findById(codigo);
		return acomodacao.isPresent() ? ResponseEntity.ok(acomodacao.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_ACOMODACAO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		acomodacaoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ACOMODACAO') and #oauth2.hasScope('write')")
	public ResponseEntity<Acomodacao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Acomodacao acomodacao){
		try {
			Acomodacao acomodacaoSalva = acomodacaoService.atualizar(codigo, acomodacao);			
			return ResponseEntity.ok(acomodacaoSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}	
}

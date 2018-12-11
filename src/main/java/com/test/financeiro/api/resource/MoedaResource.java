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

import com.test.financeiro.api.entities.Moeda;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.repository.MoedaRepository;
import com.test.financeiro.api.repository.filter.MoedaFilter;
import com.test.financeiro.api.service.MoedaService;

@RestController
@RequestMapping("/moedas")
public class MoedaResource {

	@Autowired
	private MoedaRepository moedaRepository;
	
	@Autowired
	private MoedaService moedaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MOEDA') and #oauth2.hasScope('read')")		
	public Page<Moeda> pesquisar(MoedaFilter moedaFilter, Pageable pageable){
		return moedaRepository.filtrar(moedaFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MOEDA') and #oauth2.hasScope('write')")
	public ResponseEntity<Moeda> criar(@Valid @RequestBody Moeda moeda, HttpServletResponse response) {
		
		Moeda moedaSalva = moedaRepository.save(moeda);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, moedaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(moedaSalva);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MOEDA') and #oauth2.hasScope('read')")
	public ResponseEntity<Moeda> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Moeda> moeda =  moedaRepository.findById(codigo);
		return moeda.isPresent() ? ResponseEntity.ok(moeda.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_MOEDA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		moedaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MOEDA') and #oauth2.hasScope('write')")
	public ResponseEntity<Moeda> atualizar(@PathVariable Long codigo, @Valid @RequestBody Moeda moeda){
		try {
			Moeda moedaSalva = moedaService.atualizar(codigo, moeda);			
			return ResponseEntity.ok(moedaSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}	
	
}

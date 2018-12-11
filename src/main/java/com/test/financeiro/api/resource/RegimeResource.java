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

import com.test.financeiro.api.entities.Regime;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.repository.RegimeRepository;
import com.test.financeiro.api.repository.filter.RegimeFilter;
import com.test.financeiro.api.service.RegimeService;

@RestController
@RequestMapping("/regimes")
public class RegimeResource {

	@Autowired
	private RegimeRepository regimeRepository;
	
	@Autowired
	private RegimeService regimeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_REGIME') and #oauth2.hasScope('read')")		
	public Page<Regime> pesquisar(RegimeFilter regimeFilter, Pageable pageable){
		return regimeRepository.filtrar(regimeFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_REGIME') and #oauth2.hasScope('write')")
	public ResponseEntity<Regime> criar(@Valid @RequestBody Regime regime, HttpServletResponse response) {
		
		Regime regimeSalva = regimeRepository.save(regime);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, regimeSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(regimeSalva);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_REGIME') and #oauth2.hasScope('read')")
	public ResponseEntity<Regime> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Regime> regime =  regimeRepository.findById(codigo);
		return regime.isPresent() ? ResponseEntity.ok(regime.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_REGIME') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		regimeRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_REGIME') and #oauth2.hasScope('write')")
	public ResponseEntity<Regime> atualizar(@PathVariable Long codigo, @Valid @RequestBody Regime regime){
		try {
			Regime regimeSalva = regimeService.atualizar(codigo, regime);			
			return ResponseEntity.ok(regimeSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}	

}

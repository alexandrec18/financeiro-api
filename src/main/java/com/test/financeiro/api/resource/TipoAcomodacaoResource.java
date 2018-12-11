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

import com.test.financeiro.api.entities.TipoAcomodacao;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.repository.TipoAcomodacaoRepository;
import com.test.financeiro.api.repository.filter.TipoAcomodacaoFilter;
import com.test.financeiro.api.service.TipoAcomodacaoService;

@RestController
@RequestMapping("/tipo-acomodacoes")
public class TipoAcomodacaoResource {

	@Autowired
	private TipoAcomodacaoRepository tipoAcomodacaoRepository;
	
	@Autowired
	private TipoAcomodacaoService tipoAcomodacaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_ACOMODACAO') and #oauth2.hasScope('read')")		
	public Page<TipoAcomodacao> pesquisar(TipoAcomodacaoFilter tipoAcomodacaoFilter, Pageable pageable){
		return tipoAcomodacaoRepository.filtrar(tipoAcomodacaoFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TIPO_ACOMODACAO') and #oauth2.hasScope('write')")
	public ResponseEntity<TipoAcomodacao> criar(@Valid @RequestBody TipoAcomodacao tipoAcomodacao, HttpServletResponse response) {
		
		TipoAcomodacao tipoAcomodacaoSalva = tipoAcomodacaoRepository.save(tipoAcomodacao);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoAcomodacaoSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoAcomodacaoSalva);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_ACOMODACAO') and #oauth2.hasScope('read')")
	public ResponseEntity<TipoAcomodacao> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<TipoAcomodacao> tipoAcomodacao =  tipoAcomodacaoRepository.findById(codigo);
		return tipoAcomodacao.isPresent() ? ResponseEntity.ok(tipoAcomodacao.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_TIPO_ACOMODACAO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		tipoAcomodacaoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TIPO_ACOMODACAO') and #oauth2.hasScope('write')")
	public ResponseEntity<TipoAcomodacao> atualizar(@PathVariable Long codigo, @Valid @RequestBody TipoAcomodacao tipoAcomodacao){
		try {
			TipoAcomodacao tipoAcomodacaoSalva = tipoAcomodacaoService.atualizar(codigo, tipoAcomodacao);			
			return ResponseEntity.ok(tipoAcomodacaoSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}	
}

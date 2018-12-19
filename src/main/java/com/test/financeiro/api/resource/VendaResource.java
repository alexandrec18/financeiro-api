package com.test.financeiro.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.financeiro.api.entities.Venda;
import com.test.financeiro.api.event.RecursoCriadoEvent;
import com.test.financeiro.api.exceptionhandler.FinanceiroExceptionHandler.Erro;
import com.test.financeiro.api.repository.VendaRepository;
import com.test.financeiro.api.repository.filter.VendaFilter;
import com.test.financeiro.api.repository.projection.ResumoVenda;
import com.test.financeiro.api.service.VendaService;
import com.test.financeiro.api.service.exception.PaganteInexistenteOuInativoException;
import com.test.financeiro.api.service.exception.VendedorInexistenteOuInativoException;

@RestController
@RequestMapping("/vendas")
public class VendaResource {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	VendaService vendaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/relatorios/por-documento")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<byte[]> relatorioPorDocumento(			
			@RequestParam Long codigo) throws Exception {
		byte[] relatorio = vendaService.relatorioPorDocumento(codigo);
		
		HttpHeaders headers = new HttpHeaders();
		  headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
		  headers.add("Content-Disposition", "attachment; filename=porDocumento.pdf");
		
		return ResponseEntity.ok()
				.headers(headers)
				.body(relatorio);
	}
	
	@GetMapping	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_VENDA') and #oauth2.hasScope('read')")
	public Page<Venda> pesquisar(VendaFilter vendaFilter, Pageable pageable){
		return vendaRepository.filtrar(vendaFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_VENDA') and #oauth2.hasScope('read')")
	public Page<ResumoVenda> resumir(VendaFilter vendaFilter, Pageable pageable){
		return vendaRepository.resumir(vendaFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_VENDA') and #oauth2.hasScope('read')")
	public ResponseEntity<Venda> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Venda> venda =  vendaRepository.findById(codigo);
		return venda.isPresent() ? ResponseEntity.ok(venda.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_VENDA') and #oauth2.hasScope('write')")
	public ResponseEntity<Venda> criar(@Valid @RequestBody Venda venda, HttpServletResponse response){
		Venda vendaSalva = vendaService.salvar(venda);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, vendaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_VENDA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		vendaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_VENDA') and #oauth2.hasScope('write')")
	public ResponseEntity<Venda> atualizar(@PathVariable Long codigo, @Valid @RequestBody Venda venda){
		try {
			Venda vendaSalva = vendaService.atualizar(codigo, venda);			
			return ResponseEntity.ok(vendaSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ExceptionHandler( {VendedorInexistenteOuInativoException.class} )
	public ResponseEntity<Object> handleVendedorInexistenteOuInativaException(VendedorInexistenteOuInativoException ex){
		String messagemUsuario = messageSource.getMessage("vendedor.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler( {PaganteInexistenteOuInativoException.class} )
	public ResponseEntity<Object> handlePaganteInexistenteOuInativaException(PaganteInexistenteOuInativoException ex){
		String messagemUsuario = messageSource.getMessage("pagante.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
		
}

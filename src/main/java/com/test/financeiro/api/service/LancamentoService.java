package com.test.financeiro.api.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.dto.LancamentoEstatisticaPessoa;
import com.test.financeiro.api.dto.LancamentoPorPeriodo;
import com.test.financeiro.api.entities.Lancamento;
import com.test.financeiro.api.entities.Pessoa;
import com.test.financeiro.api.repository.LancamentoRepository;
import com.test.financeiro.api.repository.PessoaRepository;
import com.test.financeiro.api.service.exception.PessoaInexistenteOuInativaException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class LancamentoService {
	
//	private static final String DESTINATARIOS = "ROLE_PESQUISAR_LANCAMENTO";
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
//	@Autowired
//	private UsuarioRepository usuarioRepository;	
//	
//	@Autowired
//	private Mailer mailer;
//	
//	Quando for usar fazer os testes por empresa
//	@Scheduled(cron = "0 0 6 * * *")
//	public void avisarSobreLancamentosVencidos() {		
//		List<Lancamento> vencidos = 
//				lancamentoRepository.findByDataVencimentoLessThanEqualAndDataPagamentoIsNull(LocalDate.now());
//		
//		List<Usuario> destinatarios = 
//				usuarioRepository.findByPermissoesDescricao(DESTINATARIOS);
//		
//		mailer.avisarSobreLancamentosVencidos(vencidos, destinatarios);
//	}
	
	public byte[] relatorioPorPeriodo(LocalDate inicio, LocalDate fim, Long empresa) throws Exception {
		List<LancamentoPorPeriodo> dados = lancamentoRepository.porPeriodo(inicio, fim, empresa);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/lancamentos-por-periodo.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public byte[] relatorioPorPessoa(LocalDate inicio, LocalDate fim, Long empresa) throws Exception {
		List<LancamentoEstatisticaPessoa> dados = lancamentoRepository.porPessoa(inicio, fim, empresa);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/lancamentos-por-pessoa.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public Lancamento salvar(Lancamento lancamento) {
		
		validarPessoa(lancamento);
		
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		
		if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);		
	}

	private Lancamento buscarLancamentoExistente(Long codigo) {
		Optional<Lancamento> lancamentoSalvo = lancamentoRepository.findById(codigo);
		
		if (!lancamentoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo.get();
	}
	
	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		
		if (lancamento.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());
		}
		
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}		
	}	
	
}

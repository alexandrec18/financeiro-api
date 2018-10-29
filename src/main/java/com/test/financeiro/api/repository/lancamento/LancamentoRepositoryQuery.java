package com.test.financeiro.api.repository.lancamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.dto.LancamentoEstatisticaCategoria;
import com.test.financeiro.api.dto.LancamentoEstatisticaDia;
import com.test.financeiro.api.dto.LancamentoEstatisticaPessoa;
import com.test.financeiro.api.entities.Lancamento;
import com.test.financeiro.api.repository.filter.LancamentoFilter;
import com.test.financeiro.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public List<LancamentoEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim);
	public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);
	public List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia);
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}

package com.test.financeiro.api.repository.pessoa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.financeiro.api.entities.Pessoa;
import com.test.financeiro.api.repository.filter.PessoaFilter;
import com.test.financeiro.api.repository.projection.ResumoPessoa;

public interface PessoaRepositoryQuery {
	
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);
	public Page<ResumoPessoa> resumir(PessoaFilter pessoaFilter, Pageable pageable);
	public List<ResumoPessoa> listar(PessoaFilter pessoaFilter);
	
}

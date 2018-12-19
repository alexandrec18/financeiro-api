package com.test.financeiro.api.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.test.financeiro.api.entities.Pessoa;
import com.test.financeiro.api.repository.filter.PessoaFilter;
import com.test.financeiro.api.repository.projection.ResumoPessoa;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Pessoa> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pessoaFilter));
	}

	@Override
	public Page<ResumoPessoa> resumir(PessoaFilter pessoaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPessoa> criteria = builder.createQuery(ResumoPessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		criteria.select(builder.construct(ResumoPessoa.class, 
				  root.get("codigo"), root.get("nome"),
				  root.get("endereco").get("cidade").get("nome"),
				  root.get("endereco").get("cidade").get("estado").get("nome"),
				  root.get("ativo")));

		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoPessoa> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pessoaFilter));
	}
	
	@Override
	public List<ResumoPessoa> listar(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPessoa> criteria = builder.createQuery(ResumoPessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		criteria.select(builder.construct(ResumoPessoa.class, 
				  root.get("codigo"), root.get("nome"),
				  root.get("endereco").get("cidade").get("nome"),
				  root.get("endereco").get("cidade").get("estado").get("nome"),
				  root.get("ativo")));

		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoPessoa> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}	

	
	private Predicate[] criarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder,
			Root<Pessoa> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(pessoaFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + pessoaFilter.getNome().toLowerCase() + "%"));			
		}				
		
		if (pessoaFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), pessoaFilter.getEmpresa()));
		} 

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);		
	}	
	
	private Long total(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}
	
}

package com.test.financeiro.api.repository.acomodacao;

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

import com.test.financeiro.api.entities.Acomodacao;
import com.test.financeiro.api.repository.filter.AcomodacaoFilter;

public class AcomodacaoRepositoryImpl implements AcomodacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Acomodacao> filtrar(AcomodacaoFilter acomodacaoFilter, Pageable pageable) {		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Acomodacao> criteria = builder.createQuery(Acomodacao.class);
		
		Root<Acomodacao> root = criteria.from(Acomodacao.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(acomodacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Acomodacao> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(acomodacaoFilter));
	}
	
	private Predicate[] criarRestricoes(AcomodacaoFilter acomodacaoFilter, CriteriaBuilder builder,
			Root<Acomodacao> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(acomodacaoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + acomodacaoFilter.getNome().toLowerCase() + "%"));			
		}				
		
		if (acomodacaoFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), acomodacaoFilter.getEmpresa()));
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
	
	private Long total(AcomodacaoFilter acomodacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Acomodacao> root = criteria.from(Acomodacao.class);
		
		Predicate[] predicates = criarRestricoes(acomodacaoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}	

}

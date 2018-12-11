package com.test.financeiro.api.repository.regime;

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

import com.test.financeiro.api.entities.Regime;
import com.test.financeiro.api.repository.filter.RegimeFilter;

public class RegimeRepositoryImpl implements RegimeRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Regime> filtrar(RegimeFilter regimeFilter, Pageable pageable) {		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Regime> criteria = builder.createQuery(Regime.class);
		
		Root<Regime> root = criteria.from(Regime.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(regimeFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Regime> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(regimeFilter));
	}
	
	private Predicate[] criarRestricoes(RegimeFilter regimeFilter, CriteriaBuilder builder,
			Root<Regime> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(regimeFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + regimeFilter.getNome().toLowerCase() + "%"));			
		}				
		
		if (regimeFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), regimeFilter.getEmpresa()));
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
	
	private Long total(RegimeFilter regimeFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Regime> root = criteria.from(Regime.class);
		
		Predicate[] predicates = criarRestricoes(regimeFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}	

}

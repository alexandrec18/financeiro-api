package com.test.financeiro.api.repository.transporte;

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

import com.test.financeiro.api.entities.Transporte;
import com.test.financeiro.api.repository.filter.TransporteFilter;

public class TransporteRepositoryImpl implements TransporteRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Transporte> filtrar(TransporteFilter transporteFilter, Pageable pageable) {		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Transporte> criteria = builder.createQuery(Transporte.class);
		
		Root<Transporte> root = criteria.from(Transporte.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(transporteFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Transporte> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(transporteFilter));
	}
	
	private Predicate[] criarRestricoes(TransporteFilter transporteFilter, CriteriaBuilder builder,
			Root<Transporte> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(transporteFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + transporteFilter.getNome().toLowerCase() + "%"));			
		}				
		
		if (transporteFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), transporteFilter.getEmpresa()));
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
	
	private Long total(TransporteFilter transporteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Transporte> root = criteria.from(Transporte.class);
		
		Predicate[] predicates = criarRestricoes(transporteFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}	

}

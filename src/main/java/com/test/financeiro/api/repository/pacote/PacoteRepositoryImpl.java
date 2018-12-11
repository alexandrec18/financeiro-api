package com.test.financeiro.api.repository.pacote;

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

import com.test.financeiro.api.entities.Pacote;
import com.test.financeiro.api.repository.filter.PacoteFilter;

public class PacoteRepositoryImpl implements PacoteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Pacote> filtrar(PacoteFilter pacoteFilter, Pageable pageable) {		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pacote> criteria = builder.createQuery(Pacote.class);
		
		Root<Pacote> root = criteria.from(Pacote.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(pacoteFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Pacote> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pacoteFilter));
	}
	
	private Predicate[] criarRestricoes(PacoteFilter pacoteFilter, CriteriaBuilder builder,
			Root<Pacote> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(pacoteFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + pacoteFilter.getNome().toLowerCase() + "%"));			
		}				
		
		if (pacoteFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), pacoteFilter.getEmpresa()));
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
	
	private Long total(PacoteFilter pacoteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pacote> root = criteria.from(Pacote.class);
		
		Predicate[] predicates = criarRestricoes(pacoteFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}	

}

package com.test.financeiro.api.repository.moeda;

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

import com.test.financeiro.api.entities.Moeda;
import com.test.financeiro.api.repository.filter.MoedaFilter;

public class MoedaRepositoryImpl implements MoedaRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Moeda> filtrar(MoedaFilter moedaFilter, Pageable pageable) {		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Moeda> criteria = builder.createQuery(Moeda.class);
		
		Root<Moeda> root = criteria.from(Moeda.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(moedaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Moeda> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(moedaFilter));
	}
	
	private Predicate[] criarRestricoes(MoedaFilter moedaFilter, CriteriaBuilder builder,
			Root<Moeda> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(moedaFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + moedaFilter.getNome().toLowerCase() + "%"));			
		}				
		
		if (moedaFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), moedaFilter.getEmpresa()));
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
	
	private Long total(MoedaFilter moedaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Moeda> root = criteria.from(Moeda.class);
		
		Predicate[] predicates = criarRestricoes(moedaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}	


}

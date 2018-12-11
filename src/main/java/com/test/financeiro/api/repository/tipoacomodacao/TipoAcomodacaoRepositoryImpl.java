package com.test.financeiro.api.repository.tipoacomodacao;

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

import com.test.financeiro.api.entities.TipoAcomodacao;
import com.test.financeiro.api.repository.filter.TipoAcomodacaoFilter;

public class TipoAcomodacaoRepositoryImpl implements TipoAcomodacaoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<TipoAcomodacao> filtrar(TipoAcomodacaoFilter tipoAcomodacaoFilter, Pageable pageable) {		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TipoAcomodacao> criteria = builder.createQuery(TipoAcomodacao.class);
		
		Root<TipoAcomodacao> root = criteria.from(TipoAcomodacao.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(tipoAcomodacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<TipoAcomodacao> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(tipoAcomodacaoFilter));
	}
	
	private Predicate[] criarRestricoes(TipoAcomodacaoFilter tipoAcomodacaoFilter, CriteriaBuilder builder,
			Root<TipoAcomodacao> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(tipoAcomodacaoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + tipoAcomodacaoFilter.getNome().toLowerCase() + "%"));			
		}				
		
		if (tipoAcomodacaoFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), tipoAcomodacaoFilter.getEmpresa()));
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
	
	private Long total(TipoAcomodacaoFilter tipoAcomodacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<TipoAcomodacao> root = criteria.from(TipoAcomodacao.class);
		
		Predicate[] predicates = criarRestricoes(tipoAcomodacaoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}	

}

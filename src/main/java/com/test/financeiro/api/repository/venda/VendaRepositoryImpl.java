package com.test.financeiro.api.repository.venda;

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

import com.test.financeiro.api.entities.Venda;
import com.test.financeiro.api.repository.filter.VendaFilter;
import com.test.financeiro.api.repository.projection.ResumoVenda;

public class VendaRepositoryImpl implements VendaRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Venda> filtrar(VendaFilter vendaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Venda> criteria = builder.createQuery(Venda.class);
		
		Root<Venda> root = criteria.from(Venda.class);
		
		//restricoes
		Predicate[] predicates = criarRestricoes(vendaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Venda> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(vendaFilter));
	}
	
	@Override
	public Page<ResumoVenda> resumir(VendaFilter vendaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoVenda> criteria = builder.createQuery(ResumoVenda.class);
		Root<Venda> root = criteria.from(Venda.class);
		
		criteria.select(builder.construct(ResumoVenda.class, 
				  root.get("codigo"), root.get("dataVenda")
				, root.get("situacao")
				, root.get("pagante").get("nome")
				, root.get("numero")
				, root.get("totalProdutos")
				, root.get("totalFinal")));

		Predicate[] predicates = criarRestricoes(vendaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoVenda> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(vendaFilter));
	}	

	
	private Predicate[] criarRestricoes(VendaFilter vendaFilter, CriteriaBuilder builder,
			Root<Venda> root) {
		
		List<Predicate> predicates = new ArrayList<>();				
		
		if (vendaFilter.getPeriodoInicial() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("periodoInicial"), 
							vendaFilter.getPeriodoInicial()));
		}
		
		if (vendaFilter.getPeriodoFinal() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("periodoFinal"), 
							vendaFilter.getPeriodoFinal()));
		}
		
		if (vendaFilter.getEmpresa() != 0) {
			predicates.add(
					builder.equal(root.get("empresa"), vendaFilter.getEmpresa()));
		}
		
		if (!StringUtils.isEmpty(vendaFilter.getSituacao())) {
			predicates.add(
					builder.equal(root.get("situacao"), vendaFilter.getSituacao()));
		}
		
		if (!StringUtils.isEmpty(vendaFilter.getPagante())) {
			
			
			predicates.add(builder.like(
					builder.lower(root.get("pagante").get("nome")), "%" + vendaFilter.getPagante().toLowerCase() + "%"));
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

	private Long total(VendaFilter vendaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Venda> root = criteria.from(Venda.class);
		
		Predicate[] predicates = criarRestricoes(vendaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
				
		return manager.createQuery(criteria).getSingleResult();
	}
}

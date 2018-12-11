package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Empresa;
import com.test.financeiro.api.entities.Pessoa;
import com.test.financeiro.api.entities.Usuario;
import com.test.financeiro.api.entities.Venda;
import com.test.financeiro.api.entities.VendaNumero;
import com.test.financeiro.api.repository.PessoaRepository;
import com.test.financeiro.api.repository.UsuarioRepository;
import com.test.financeiro.api.repository.VendaNumeroRepository;
import com.test.financeiro.api.repository.VendaRepository;
import com.test.financeiro.api.service.exception.PaganteInexistenteOuInativoException;
import com.test.financeiro.api.service.exception.VendedorInexistenteOuInativoException;

@Service
public class VendaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VendaNumeroRepository vendaNumeroRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
    public Venda salvar(Venda venda) {
		
		validarVendedor(venda);
		validarPagante(venda);
		
		venda.getVendaProduto().forEach(vp -> vp.setVenda(venda));
		venda.getVendaFormaPagamento().forEach(vfp -> vfp.setVenda(venda));		
		
		
		venda.getVendaProduto().forEach(vp -> 
		  vp.getVendaProdutoPassageiro().forEach(vpp -> 
		    vpp.setVendaProduto(vp)));
		
		venda.getVendaProduto().forEach(vp -> 
		  vp.getVendaProdutoTrecho().forEach(vpt -> 
		    vpt.setVendaProduto(vp)));
		
		venda.getVendaFormaPagamento().forEach(vfp -> 
		  vfp.getVendaFormaPagamentoProduto().forEach(vfpp -> 
		    vfpp.setVendaFormaPagamento(vfp)));
		
		venda.setNumero(retornarVendaNumero(venda.getEmpresa()));
		
		return vendaRepository.save(venda);
	}
    
    public Venda atualizar(Long codigo, Venda venda) {
		Venda vendaSalva = buscarVendaExistente(codigo);
				
		validarPessoas(vendaSalva, venda);
		
		vendaSalva.getVendaProduto().clear();
		vendaSalva.getVendaProduto().addAll(venda.getVendaProduto());
		vendaSalva.getVendaProduto().forEach(vp -> vp.setVenda(vendaSalva));
		
		vendaSalva.getVendaFormaPagamento().clear();
		vendaSalva.getVendaFormaPagamento().addAll(venda.getVendaFormaPagamento());
		vendaSalva.getVendaFormaPagamento().forEach(vfp -> vfp.setVenda(vendaSalva));		
		
		vendaSalva.getVendaProduto().forEach(vp -> 
		  vp.getVendaProdutoPassageiro().forEach(vpp -> 
		    vpp.setVendaProduto(vp)));
		
		vendaSalva.getVendaProduto().forEach(vp -> 
		  vp.getVendaProdutoTrecho().forEach(vpt -> 
		    vpt.setVendaProduto(vp)));
		
		vendaSalva.getVendaFormaPagamento().forEach(vfp -> 
		  vfp.getVendaFormaPagamentoProduto().forEach(vfpp -> 
		    vfpp.setVendaFormaPagamento(vfp)));		    
		
		BeanUtils.copyProperties(venda, vendaSalva, "codigo", "vendaFormaPagamento", "vendaProduto");
		return vendaRepository.save(vendaSalva);		
	}
    
    private Long retornarVendaNumero(Empresa empresa) {
    	
    	VendaNumero vendaNumero = vendaNumeroRepository.findByEmpresa(empresa);
    	
    	if (vendaNumero == null) {    		
    		
    		VendaNumero vendaNumeroSalvar = new VendaNumero();
    		vendaNumeroSalvar.setNumero((long) 1);
    		vendaNumeroSalvar.setEmpresa(empresa);
    		
    		vendaNumeroRepository.save(vendaNumeroSalvar);
    		
    		return (long) 1;
    		
		} else {
			
			Long numero = vendaNumero.getNumero() + 1;
			
			vendaNumero.setNumero(numero);
			vendaNumeroRepository.save(vendaNumero);
			
			return numero;
			
		}
    	
    }

	private Venda buscarVendaExistente(Long codigo) {
		Optional<Venda> vendaSalva = vendaRepository.findById(codigo);
		
		if (!vendaSalva.isPresent()) {
			throw new IllegalArgumentException();
		}
		return vendaSalva.get();
	}
    
    private void validarVendedor(Venda venda) {
		Usuario usuario = null;
		
		if (venda.getVendedor().getCodigo() != null) {
			usuario = usuarioRepository.getOne(venda.getVendedor().getCodigo());
		}
		
		if (usuario == null) {
			throw new VendedorInexistenteOuInativoException();
		}		
	}
    
    private void validarPagante(Venda venda) {
		Pessoa pessoa = null;
		
		if (venda.getPagante().getCodigo() != null) {
			pessoa = pessoaRepository.getOne(venda.getPagante().getCodigo());
		}
		
		if (pessoa == null || pessoa.isInativo()) {
			throw new PaganteInexistenteOuInativoException();
		}		
	}        
    
    private void validarPessoas(Venda vendaSalva, Venda venda) {
    	
    	if (!venda.getVendedor().equals(vendaSalva.getVendedor())) {
			validarVendedor(venda);
		}
		
		if (!venda.getPagante().equals(vendaSalva.getPagante())) {
			validarPagante(venda);
		}								
		
    }
}

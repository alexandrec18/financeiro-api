package com.test.financeiro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.financeiro.api.entities.Usuario;
import com.test.financeiro.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioPeloCodigo(codigo);
		
		if (!usuario.getSenha().equals(usuarioSalvo.getSenha())) {
			gerarSenha(usuario);
		}
		
		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
		return usuarioRepository.save(usuarioSalvo);		
	}		

	public Usuario buscarUsuarioPeloCodigo(Long codigo) {
		Optional<Usuario> usuarioSalvo = usuarioRepository.findById(codigo);
		
		if (!usuarioSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioSalvo.get();
	}
	
	public void gerarSenha(Usuario usuario) {
		String senhaDigitada = usuario.getSenha();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		senhaDigitada = encoder.encode(senhaDigitada);
		
		usuario.setSenha(senhaDigitada);
	}

}

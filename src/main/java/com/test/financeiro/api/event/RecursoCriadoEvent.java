package com.test.financeiro.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse respose;
	private Long codigo;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);	
		this.respose = response;
		this.codigo  = codigo;
	}

	public HttpServletResponse getRespose() {
		return respose;
	}

	public Long getCodigo() {
		return codigo;
	}
	

}

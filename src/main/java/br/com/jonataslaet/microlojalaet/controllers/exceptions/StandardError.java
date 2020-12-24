package br.com.jonataslaet.microlojalaet.controllers.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer valorStatusHttp;
	private String mensagem;
	private Long instante;
	
	public StandardError(Integer valorStatusHttp, String mensagem, Long instante) {
		super();
		this.valorStatusHttp = valorStatusHttp;
		this.mensagem = mensagem;
		this.instante = instante;
	}

	public Integer getValorStatusHttp() {
		return valorStatusHttp;
	}

	public void setValorStatusHttp(Integer valorStatusHttp) {
		this.valorStatusHttp = valorStatusHttp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getInstante() {
		return instante;
	}

	public void setInstante(Long instante) {
		this.instante = instante;
	}
}

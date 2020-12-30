package br.com.jonataslaet.microlojalaet.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	
	private static final long serialVersionUID = 1L;
	
	private List<CustomFieldError> errors = new ArrayList<>();
	
	public ValidationError(Integer valorStatusHttp, String mensagem, Long instante) {
		super(valorStatusHttp, mensagem, instante);
	}

	public List<CustomFieldError> getErrors() {
		return this.errors;
	}
	
}

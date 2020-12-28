package br.com.jonataslaet.microlojalaet.controllers.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable ex) {
		super(msg, ex);
	}
}

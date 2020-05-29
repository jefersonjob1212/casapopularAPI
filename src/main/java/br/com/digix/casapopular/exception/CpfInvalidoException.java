package br.com.digix.casapopular.exception;

public class CpfInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CpfInvalidoException() {
		super("O CPF informado é inválido");
	}

}

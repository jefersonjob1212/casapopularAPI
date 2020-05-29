package br.com.digix.casapopular.exception;

public class FamiliaSemPretendenteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FamiliaSemPretendenteException() {
		super("Família não possui pretendente");
	}

}

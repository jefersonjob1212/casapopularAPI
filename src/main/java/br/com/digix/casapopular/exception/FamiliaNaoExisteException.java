package br.com.digix.casapopular.exception;

public class FamiliaNaoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FamiliaNaoExisteException() {
		super("Família não encontrada");
	}

}

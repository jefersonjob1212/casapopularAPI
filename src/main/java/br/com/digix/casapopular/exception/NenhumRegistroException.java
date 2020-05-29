package br.com.digix.casapopular.exception;

public class NenhumRegistroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NenhumRegistroException() {
		super("Nenhum registro encontrado");
	}

}

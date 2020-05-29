package br.com.digix.casapopular.exception;

public class CampoVazioOuNuloException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CampoVazioOuNuloException(String campo) {
		super("O campo " + campo + " é obrigatório");
	}

}

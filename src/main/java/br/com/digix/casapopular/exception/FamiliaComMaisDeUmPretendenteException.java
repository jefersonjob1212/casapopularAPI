package br.com.digix.casapopular.exception;

public class FamiliaComMaisDeUmPretendenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FamiliaComMaisDeUmPretendenteException() {
		super("Não é permitido ter mais de um pretendente por família");
	}

}

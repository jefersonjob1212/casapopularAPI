package br.com.digix.casapopular.exception;

public class FamiliaSemPessoaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FamiliaSemPessoaException() {
		super("Nenhuma pessoa foi informada. Favor informe ao menos uma pessoa como pretendente");
	}

}

package br.com.digix.casapopular.exception;

public class ExistePessoaCpfException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExistePessoaCpfException() {
		super("Já existe uma pessoa cadastrada com este CPF. Verifique seu cadastro e tente novamente");
	}

}

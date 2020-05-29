package br.com.digix.casapopular.exception;

public class PessoaCpfNaoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PessoaCpfNaoExisteException() {
		super("Não existe pessoa com este CPF. Favor verificar.");
	}

}

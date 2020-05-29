package br.com.digix.casapopular.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptionController {

	@ExceptionHandler(value = CpfInvalidoException.class)
	public ResponseEntity<Object> cpfInvalidoException(CpfInvalidoException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ExistePessoaCpfException.class)
	public ResponseEntity<Object> existePessoaCpfException(ExistePessoaCpfException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = PessoaCpfNaoExisteException.class)
	public ResponseEntity<Object> pessoaCpfNaoExisteException(PessoaCpfNaoExisteException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(value = NenhumRegistroException.class)
	public ResponseEntity<Object> nenhumRegistroException(NenhumRegistroException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(value = CampoVazioOuNuloException.class)
	public ResponseEntity<Object> campoVazioOuNuloException(CampoVazioOuNuloException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = FamiliaSemPessoaException.class)
	public ResponseEntity<Object> familiaSemPessoaException(FamiliaSemPessoaException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = FamiliaComMaisDeUmPretendenteException.class)
	public ResponseEntity<Object> familiaComMaisDeUmPretendenteException(FamiliaComMaisDeUmPretendenteException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = FamiliaSemPretendenteException.class)
	public ResponseEntity<Object> familiaSemPretendenteException(FamiliaSemPretendenteException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}

package br.com.digix.casapopular.services;

import br.com.digix.casapopular.dto.PessoaDTO;
import br.com.digix.casapopular.services.generic.ServiceGeneric;

public interface PessoaService extends ServiceGeneric<PessoaDTO> {
	
	PessoaDTO buscarPorCpf(String cpf) throws Exception;

}

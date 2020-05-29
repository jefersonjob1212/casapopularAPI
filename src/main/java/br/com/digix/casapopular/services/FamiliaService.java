package br.com.digix.casapopular.services;

import java.util.List;

import br.com.digix.casapopular.dto.FamiliaDTO;
import br.com.digix.casapopular.services.generic.ServiceGeneric;

public interface FamiliaService extends ServiceGeneric<FamiliaDTO> {
	
	FamiliaDTO buscarPorPessoasCpf(String cpf);
	
	List<FamiliaDTO> buscarFamiliasParaValidar();
	
	void alterarValidacaoFamiliaContemplada(String id) throws Exception;
}

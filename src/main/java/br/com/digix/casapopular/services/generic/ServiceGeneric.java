package br.com.digix.casapopular.services.generic;

import java.util.List;

public interface ServiceGeneric<T> {
	
	T salvar(T dto) throws Exception;
	
	T buscarPorId(String id) throws Exception;
	
	List<T> buscarTodosPaginadoOrdenado(Integer pagina, Integer qtdPagina, String coluna, String ascOuDesc) throws Exception;
	
	void removerPorId(String id);
}

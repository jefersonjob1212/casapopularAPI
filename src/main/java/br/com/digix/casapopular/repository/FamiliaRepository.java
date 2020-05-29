package br.com.digix.casapopular.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.digix.casapopular.model.Familia;

public interface FamiliaRepository extends MongoRepository<Familia, String> {
	
	List<Familia> findByValidada(Boolean validada);
	
	List<Familia> findByPessoasNome(String nome);
	
	List<Familia> findByPessoasCpf(String cpf);

}

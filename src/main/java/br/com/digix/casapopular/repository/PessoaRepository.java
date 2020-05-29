package br.com.digix.casapopular.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.digix.casapopular.model.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {
	
	Optional<Pessoa> findByCpf(String cpf);

}

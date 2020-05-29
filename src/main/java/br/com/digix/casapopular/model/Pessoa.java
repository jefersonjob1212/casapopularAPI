package br.com.digix.casapopular.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.digix.casapopular.enums.TipoPessoaEnum;
import lombok.Data;

@Data
@Document(collection = "pessoa")
public class Pessoa {
	
	private String id;
	
	private String nome;
	
	private String cpf;
	
	private TipoPessoaEnum tipo;
	
	private Instant dataNascimento;
	
	private BigDecimal valorRenda;
}

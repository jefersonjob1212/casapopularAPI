package br.com.digix.casapopular.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "familia")
public class Familia {

	@Id
	private String id;
	
	@DBRef
	private List<Pessoa> pessoas;
	
	private Boolean validada = false;
}

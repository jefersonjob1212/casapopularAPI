package br.com.digix.casapopular.dto;

import java.util.List;

import lombok.Data;

@Data
public class FamiliaDTO {
	
	private String id;
	
	private List<PessoaDTO> pessoas;
	
	private Boolean validada;
}

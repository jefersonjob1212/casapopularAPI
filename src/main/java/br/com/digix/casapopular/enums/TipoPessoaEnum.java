package br.com.digix.casapopular.enums;

public enum TipoPessoaEnum {
	
	PRETENDENTE("Pretendente"),
	CONJUGE("Cônjuge"),
	DEPENDENTE("Dependente");
	
	private String descricao;
	
	TipoPessoaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

package br.com.digix.casapopular.dto;

import java.math.BigDecimal;
import java.time.Instant;

import br.com.digix.casapopular.enums.TipoPessoaEnum;
import br.com.digix.casapopular.utils.UtilsFunctions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
	
	@Setter
	private String id;
	
	@Setter
	private String nome;
	
	private String cpf;
	
	@Setter
	private TipoPessoaEnum tipo;
	
	@Setter
	private Instant dataNascimento;
	
	@Setter
	private BigDecimal valorRenda;

	public void setCpf(String cpf) {
		if(cpf.equals("")) {
			this.cpf = "";
		}
		this.cpf = UtilsFunctions.somenteNumeros(cpf);
	}

}

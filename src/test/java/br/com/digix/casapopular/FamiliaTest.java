package br.com.digix.casapopular;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.digix.casapopular.dto.FamiliaDTO;
import br.com.digix.casapopular.dto.PessoaDTO;
import br.com.digix.casapopular.enums.TipoPessoaEnum;
import br.com.digix.casapopular.utils.UtilsFunctions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FamiliaTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final String APPLICATION_JSON = "application/json";

	@Test
	public void verificaSePossuiPessoa() throws Exception {
		List<PessoaDTO> pessoas = new ArrayList<>();
		FamiliaDTO dto = new FamiliaDTO();
		dto.setPessoas(pessoas);
		
		String jsonFormatted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/familias")
				.contentType(APPLICATION_JSON)
				.content(jsonFormatted))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void verificaSeExisteDoisPretendentes() throws Exception {
		List<PessoaDTO> pessoas = Arrays.asList(
				new PessoaDTO("", "Teste1", "", TipoPessoaEnum.PRETENDENTE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste2", "", TipoPessoaEnum.PRETENDENTE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste1", "", TipoPessoaEnum.DEPENDENTE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste2", "", TipoPessoaEnum.DEPENDENTE, Instant.now(), new BigDecimal(1500))
		       );
		FamiliaDTO dto = new FamiliaDTO();
		dto.setPessoas(pessoas);
		
		String jsonFormatted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/familias")
				.contentType(APPLICATION_JSON)
				.content(jsonFormatted))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void verificaSeExisteDoisConjuges() throws Exception {
		List<PessoaDTO> pessoas = Arrays.asList(
				new PessoaDTO("", "Teste1", "", TipoPessoaEnum.PRETENDENTE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste2", "", TipoPessoaEnum.CONJUGE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste1", "", TipoPessoaEnum.CONJUGE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste2", "", TipoPessoaEnum.DEPENDENTE, Instant.now(), new BigDecimal(1500))
		       );
		FamiliaDTO dto = new FamiliaDTO();
		dto.setPessoas(pessoas);
		
		String jsonFormatted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/familias")
				.contentType(APPLICATION_JSON)
				.content(jsonFormatted))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void verificaSeNãoExisteDependente() throws Exception {
		List<PessoaDTO> pessoas = Arrays.asList(
				new PessoaDTO("", "Teste1", "", TipoPessoaEnum.DEPENDENTE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste2", "", TipoPessoaEnum.CONJUGE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste1", "", TipoPessoaEnum.CONJUGE, Instant.now(), new BigDecimal(1500)),
				new PessoaDTO("", "Teste2", "", TipoPessoaEnum.DEPENDENTE, Instant.now(), new BigDecimal(1500))
		       );
		FamiliaDTO dto = new FamiliaDTO();
		dto.setPessoas(pessoas);
		
		String jsonFormatted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/familias")
				.contentType(APPLICATION_JSON)
				.content(jsonFormatted))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void deixarFamiliaQueJaFoiValidadaEmProcesso() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.put("/v1/familias/5ecaf905de8de57d40a5ec2b"))
				.andExpect(status().isCreated());
	}

}

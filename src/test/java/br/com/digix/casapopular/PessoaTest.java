package br.com.digix.casapopular;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.digix.casapopular.dto.PessoaDTO;
import br.com.digix.casapopular.enums.TipoPessoaEnum;
import br.com.digix.casapopular.utils.UtilsFunctions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PessoaTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final String APPLICATION_JSON = "application/json";

	@Test
	public void urlNaoEncontrada() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void buscarPessoas() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/pessoas"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void validarEVerSeExistePessoa() throws Exception {
		PessoaDTO dto = new PessoaDTO();
		dto.setNome("Jeferson Job");
		dto.setCpf("42084900042");
		dto.setTipo(TipoPessoaEnum.PRETENDENTE);
		dto.setDataNascimento(Instant.now());
		dto.setValorRenda(new BigDecimal(1500));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/pessoas")
				.contentType(APPLICATION_JSON)
				.content(UtilsFunctions.convertToJson(dto)))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void validarFuncaoEhValidoCpf() throws Exception {
		assertTrue(UtilsFunctions.ehValidoCpf("03379824143"), "CPF Válido");
		assertFalse(UtilsFunctions.ehValidoCpf("11111111111"), "CPF Inválido");
		assertFalse(UtilsFunctions.ehValidoCpf("02463258514"), "CPF Inválido");
		assertTrue(UtilsFunctions.ehValidoCpf("01493380150"), "CPF Válido");
		assertTrue(UtilsFunctions.ehValidoCpf("44591292134"), "CPF Válido");
		assertFalse(UtilsFunctions.ehValidoCpf("36521487596"), "CPF Inválido");
	}
	
	@Test
	public void validarCpfPessoa() throws Exception {
		PessoaDTO dto = new PessoaDTO();
		dto.setCpf("12345678901");
		
		String jsonConverted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/pessoas")
				.contentType(APPLICATION_JSON)
				.content(jsonConverted))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarSeNomeEhNuloOuVazio() throws Exception {
		PessoaDTO dto = new PessoaDTO();
		dto.setCpf("610.837.330-01");
		dto.setTipo(TipoPessoaEnum.PRETENDENTE);
		dto.setDataNascimento(Instant.now());
		dto.setValorRenda(new BigDecimal(1500));
		
		String jsonConverted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/pessoas")
				.contentType(APPLICATION_JSON)
				.content(jsonConverted))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarSeDataNascimentoEhNuloOuVazio() throws Exception {
		PessoaDTO dto = new PessoaDTO();
		dto.setCpf("610.837.330-01");
		dto.setNome("Jeferson");
		dto.setTipo(TipoPessoaEnum.DEPENDENTE);
		dto.setValorRenda(new BigDecimal(1500));
		
		String jsonConverted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/pessoas")
				.contentType(APPLICATION_JSON)
				.content(jsonConverted))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarSeValorRendaEhNuloOuVazio() throws Exception {
		PessoaDTO dto = new PessoaDTO();
		dto.setCpf("610.837.330-01");
		dto.setNome("Jeferson");
		dto.setTipo(TipoPessoaEnum.DEPENDENTE);
		dto.setDataNascimento(Instant.now());
		
		String jsonConverted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/pessoas")
				.contentType(APPLICATION_JSON)
				.content(jsonConverted))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarSeCpfEhNuloOuVazio() throws Exception {
		PessoaDTO dto = new PessoaDTO();
		dto.setNome("Jeferson Job");
		dto.setTipo(TipoPessoaEnum.PRETENDENTE);
		dto.setDataNascimento(Instant.now());
		dto.setValorRenda(new BigDecimal(1500));
		
		String jsonConverted = UtilsFunctions.convertToJson(dto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/pessoas")
				.contentType(APPLICATION_JSON)
				.content(jsonConverted))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarSeExistePessoaComCpf() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/pessoas/cpf/610.837.330-01"))
				.andExpect(status().isNoContent());
	}

}

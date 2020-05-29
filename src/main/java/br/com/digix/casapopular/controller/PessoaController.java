package br.com.digix.casapopular.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.casapopular.dto.PessoaDTO;
import br.com.digix.casapopular.services.PessoaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@ApiOperation(
			value = "Lista de todas as pessoas cadastradas.",
			response = PessoaDTO.class,
			notes = "Método que lista as famílias cadastradas que não foram validadas"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Requisição trouxe registros",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 204,
					message = "Requisição não trouxe registros",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@GetMapping
	public ResponseEntity<?> getAllPaginateOrderedByParam(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) throws Exception{
		List<PessoaDTO> pessoas = pessoaService.buscarTodosPaginadoOrdenado(page, limit, orderBy, direction);
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "Cadastra uma pessoa.",
			response = PessoaDTO.class,
			notes = "Método que cadastra uma pessoa"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 201,
					message = "Pessoa cadastrada com sucesso",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 400,
					message = "CPF inválido"
					),
			@ApiResponse(
					code = 409,
					message = "Já existe uma pessoa cadastrada"
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@PostMapping
	public ResponseEntity<?> createPerson(@RequestBody PessoaDTO dto) throws Exception {
		PessoaDTO dtoSaved = pessoaService.salvar(dto);
		return new ResponseEntity<>(dtoSaved, HttpStatus.CREATED);
	}

	@ApiOperation(
			value = "Buscar por identificador.",
			response = PessoaDTO.class,
			notes = "Método retorna um registro"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Requisição trouxe registro",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 204,
					message = "Pessoa não existe por Identificador",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception{
		PessoaDTO dto = pessoaService.buscarPorId(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "Buscar por CPF.",
			response = PessoaDTO.class,
			notes = "Método retorna um registro por CPF"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Requisição trouxe registro",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 204,
					message = "Pessoa não existe por CPF",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<?> getByCpf(@PathVariable("cpf") String cpf) throws Exception {
		PessoaDTO dto = pessoaService.buscarPorCpf(cpf);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "Remove pessoa por Identificador.",
			response = PessoaDTO.class,
			notes = "Método remove pessoa do sistema"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Pessoa removida",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 204,
					message = "Pessoa não existe por Identificador",
					response = PessoaDTO.class
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id){
		pessoaService.removerPorId(id);
		return new ResponseEntity<>("Removed at " + Instant.now(), HttpStatus.OK);
	}
}

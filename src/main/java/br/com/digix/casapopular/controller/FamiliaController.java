package br.com.digix.casapopular.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.casapopular.dto.FamiliaDTO;
import br.com.digix.casapopular.services.FamiliaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/familias")
public class FamiliaController {

	@Autowired
	private FamiliaService familiaService;
	
	@ApiOperation(
			value = "Lista de todas as famílias cadastradas.",
			response = FamiliaDTO.class,
			notes = "Método que lista as famílias cadastradas que não foram validadas"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Requisição trouxe registros",
					response = FamiliaDTO.class
					),
			@ApiResponse(
					code = 204,
					message = "Requisição não trouxe registros",
					response = FamiliaDTO.class
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> buscarTodasParaValidar(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) throws Exception {
		List<FamiliaDTO> familias = familiaService.buscarTodosPaginadoOrdenado(page, limit, orderBy, direction);
		return new ResponseEntity<>(familias, HttpStatus.OK);
	}

	@ApiOperation(
			value = "Buscar família por CPF da pessoa",
			response = FamiliaDTO.class,
			notes = "Método retorna a família pelo CPF da pessoa"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Requisição trouxe registro",
					response = FamiliaDTO.class
					),
			@ApiResponse(
					code = 204,
					message = "Requisição não trouxe registro",
					response = FamiliaDTO.class
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@GetMapping("/pessoas/{cpf}")
	public ResponseEntity<?> buscarFamiliaPorCpfPessoa(@PathVariable("cpf") String cpf) {
		FamiliaDTO dto = familiaService.buscarPorPessoasCpf(cpf);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@ApiOperation(
			value = "Cadastra uma família.",
			response = FamiliaDTO.class,
			notes = "Método que cadastra uma família"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 201,
					message = "Família cadastrada com sucesso",
					response = FamiliaDTO.class
					),
			@ApiResponse(
					code = 400,
					message = "Família não possui pessoa"
					),
			@ApiResponse(
					code = 409,
					message = "Existem dois pretendentes na família"
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@PostMapping
	public ResponseEntity<?> save(@RequestBody FamiliaDTO dto) throws Exception {
		familiaService.salvar(dto);
		return new ResponseEntity<>("Created at " + Instant.now(), HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "Altera uma família.",
			response = FamiliaDTO.class,
			notes = "Método que altera uma família"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 201,
					message = "Família alterada com sucesso",
					response = FamiliaDTO.class
					),
			@ApiResponse(
					code = 500,
					message = "Ocorreu erro na requisição"
					)
	})
	@PutMapping("/{id}")
	public ResponseEntity<?> alteraraFamiliaContemplada(@PathVariable String id) throws Exception{
		familiaService.alterarValidacaoFamiliaContemplada(id);
		return new ResponseEntity<>("Updated at " + Instant.now(), HttpStatus.CREATED);
	}
}

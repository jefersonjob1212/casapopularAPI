package br.com.digix.casapopular.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.digix.casapopular.dto.FamiliaDTO;
import br.com.digix.casapopular.dto.PessoaDTO;
import br.com.digix.casapopular.enums.TipoPessoaEnum;
import br.com.digix.casapopular.exception.FamiliaComMaisDeUmPretendenteException;
import br.com.digix.casapopular.exception.FamiliaNaoExisteException;
import br.com.digix.casapopular.exception.FamiliaSemPessoaException;
import br.com.digix.casapopular.exception.FamiliaSemPretendenteException;
import br.com.digix.casapopular.model.Familia;
import br.com.digix.casapopular.repository.FamiliaRepository;
import br.com.digix.casapopular.services.FamiliaService;

@Service
public class FamiliaServiceImpl implements FamiliaService {
	
	@Autowired
	private FamiliaRepository familiaRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public FamiliaDTO salvar(FamiliaDTO familiaDTO) throws Exception {
		if(familiaDTO.getPessoas().isEmpty()) {
			throw new FamiliaSemPessoaException();
		} else if(verificarSeTemMaisDeUmPretendente(familiaDTO.getPessoas())) {
			throw new FamiliaComMaisDeUmPretendenteException();
		} else if(verificarSeTemMaisDeUmConjuge(familiaDTO.getPessoas())) {
			throw new FamiliaComMaisDeUmPretendenteException();
		}
		
		familiaDTO.getPessoas().stream()
				.filter(p -> p.getTipo() == TipoPessoaEnum.PRETENDENTE)
				.findFirst()
				.orElseThrow(FamiliaSemPretendenteException::new);
		
		Familia familia = familiaRepository.save(mapper.map(familiaDTO, Familia.class));
		return mapper.map(familia, FamiliaDTO.class);
	}
	
	@Override
	public List<FamiliaDTO> buscarTodosPaginadoOrdenado(Integer page, Integer size, String orderBy, String direction) {
		Sort sort = Sort.by(orderBy);
		PageRequest request = PageRequest.of(page, size, direction.equals("ASC") ? sort.ascending() : sort.descending());
		return familiaRepository.findAll(request)
				.stream()
				.map(model -> mapper.map(model, FamiliaDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public FamiliaDTO buscarPorId(String id) {
		return mapper.map(familiaRepository.findById(id).get(), FamiliaDTO.class);
	}

	@Override
	public FamiliaDTO buscarPorPessoasCpf(String cpf) {
		return mapper.map(familiaRepository.findByPessoasCpf(cpf), FamiliaDTO.class);
	}

	@Override
	public void removerPorId(String id) {
		familiaRepository.deleteById(id);	
	}
	
	private boolean verificarSeTemMaisDeUmPretendente(List<PessoaDTO> pessoas) {
		return pessoas.stream()
				.filter(pessoa -> pessoa.getTipo() == TipoPessoaEnum.PRETENDENTE)
				.count() > 1;
	}
	
	private boolean verificarSeTemMaisDeUmConjuge(List<PessoaDTO> pessoas) {
		return pessoas.stream()
				.filter(pessoa -> pessoa.getTipo() == TipoPessoaEnum.CONJUGE)
				.count() > 1;
	}

	@Override
	public List<FamiliaDTO> buscarFamiliasParaValidar() {
		// TODO Auto-generated method stub
		return familiaRepository
				.findByValidada(false)
				.stream()
				.map(familia -> mapper.map(familia, FamiliaDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void alterarValidacaoFamiliaContemplada(String id) throws Exception {
		// TODO Auto-generated method stub
		Familia familia = Optional.ofNullable(familiaRepository.findById(id))
				.get()
				.orElseThrow(FamiliaNaoExisteException::new);
		familia.setValidada(true);
		familiaRepository.save(familia);
	}
}

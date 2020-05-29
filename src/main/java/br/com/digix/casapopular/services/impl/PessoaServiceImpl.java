package br.com.digix.casapopular.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.digix.casapopular.dto.PessoaDTO;
import br.com.digix.casapopular.exception.CpfInvalidoException;
import br.com.digix.casapopular.exception.ExistePessoaCpfException;
import br.com.digix.casapopular.exception.NenhumRegistroException;
import br.com.digix.casapopular.exception.PessoaCpfNaoExisteException;
import br.com.digix.casapopular.model.Pessoa;
import br.com.digix.casapopular.repository.PessoaRepository;
import br.com.digix.casapopular.services.PessoaService;
import br.com.digix.casapopular.utils.UtilsFunctions;

@Service
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public PessoaDTO salvar(PessoaDTO dto) throws Exception {
		if(!UtilsFunctions.ehValidoCpf(dto.getCpf())) {
			throw new CpfInvalidoException();
		}
		
		Pessoa pessoa = pessoaRepository.findByCpf(UtilsFunctions.somenteNumeros(dto.getCpf()))
												.orElse(null);
		if(pessoa != null) {
			throw new ExistePessoaCpfException();
		}
		UtilsFunctions.validarObjeto(dto);
		pessoa = pessoaRepository.save(mapper.map(dto, Pessoa.class));
		return mapper.map(pessoa, PessoaDTO.class);
	}

	@Override
	public PessoaDTO buscarPorId(String id) throws Exception {
		return pessoaRepository.findById(id)
				.map(pessoa -> mapper.map(pessoa, PessoaDTO.class))
				.orElseThrow(NenhumRegistroException::new);
	}

	@Override
	public List<PessoaDTO> buscarTodosPaginadoOrdenado(Integer page, Integer size, String columnOrder,
			String direction) throws Exception {
		Sort sort = Sort.by(columnOrder);
		PageRequest request = PageRequest.of(page, size, direction.equals("ASC") ? sort.ascending() : sort.descending());
		return Optional.of(pessoaRepository.findAll(request)
				.stream()
				.map(model -> mapper.map(model, PessoaDTO.class))
				.collect(Collectors.toList()))
				.orElseThrow(NenhumRegistroException::new);
	}
	
	@Override
	public PessoaDTO buscarPorCpf(String cpf) throws Exception {
		return pessoaRepository.findByCpf(cpf)
				.map(pessoa -> mapper.map(pessoa, PessoaDTO.class))
				.orElseThrow(PessoaCpfNaoExisteException::new);
	}

	@Override
	public void removerPorId(String id) {
		pessoaRepository.deleteById(id);	
	}

}

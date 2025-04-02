package br.com.publico.servico;



import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.publico.dto.AtualizarTelefoneDto;
import br.com.publico.dto.BuscarTelefoneUtil;
import br.com.publico.dto.TelefoneUtilDto;
import br.com.publico.entidade.TelefoneUtil;
import br.com.publico.repositorio.TelefoneUtilRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelUtilServico {

	private final TelefoneUtilRepositorio telRepositorio;
	private final ModelMapper modelMapper;
	
	public TelefoneUtil criarTelefone(TelefoneUtilDto dto) {
		return telRepositorio.save(modelMapper.map(dto, TelefoneUtil.class));
	}
	
	public Page<BuscarTelefoneUtil>listarTelefones(Pageable pageable){
		return telRepositorio.findAll(pageable).map(listar -> modelMapper.map(listar, BuscarTelefoneUtil.class));
	}
	
	public TelefoneUtil buscarPorId(Long id) {
		Optional<TelefoneUtil>buscar = telRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public TelefoneUtil atualizarTelefone(AtualizarTelefoneDto dto,Long id) {
		dto.setId(id);
		return telRepositorio.save(modelMapper.map(dto, TelefoneUtil.class));
		
	}
	
	
	public TelefoneUtil buscarPorNome(String nome) {
		Optional<TelefoneUtil> buscar = telRepositorio.findByNome(nome.trim().toUpperCase());
		return buscar.orElseThrow();
	}
	
	public void excluirTelefone(Long id) {
		buscarPorId(id);
		telRepositorio.deleteById(id);
	}
}

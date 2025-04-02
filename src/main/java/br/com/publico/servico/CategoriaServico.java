package br.com.publico.servico;




import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.publico.dto.AtualizarDto;
import br.com.publico.dto.BuscarCategoriasDto;
import br.com.publico.dto.CategoriasDto;
import br.com.publico.entidade.Categoria;
import br.com.publico.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServico {
	
	private final ModelMapper modelMapper;
	private final CategoriaRepositorio categoriaRepositorio;
	
	public Categoria criarCategoria(CategoriasDto categoriasDto) {
		return categoriaRepositorio.save(modelMapper.map(categoriasDto, Categoria.class));
	}
	
	public Page<BuscarCategoriasDto>listarCategorias(Pageable pageable){
		return categoriaRepositorio.findAll(pageable)
			.map(listar -> modelMapper.map(listar, BuscarCategoriasDto.class));
		
	}
	
	public Categoria buscarPorId(Long id) {
		Optional<Categoria>buscar= categoriaRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Categoria atualizarcategoria(AtualizarDto atualizarDto,Long id) {
		atualizarDto.setId(id);
		return categoriaRepositorio.save(modelMapper.map(atualizarDto, Categoria.class));
	
	}
	
	public void excluirCategoria(Long id) {
		buscarPorId(id);
	    categoriaRepositorio.deleteById(id);
	}
	
	public Categoria buscarCategorias(String categoria) {
		Optional<Categoria>buscar = categoriaRepositorio.findByNome(categoria);
		return buscar.orElseThrow();
	}

}

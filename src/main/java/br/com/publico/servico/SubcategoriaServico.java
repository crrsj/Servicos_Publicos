package br.com.publico.servico;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.publico.dto.AtualizarSubDto;
import br.com.publico.dto.BuscarSubCategoriaDto;
import br.com.publico.dto.SubcategoriaDtO;
import br.com.publico.entidade.Subcategoria;
import br.com.publico.repositorio.CategoriaRepositorio;
import br.com.publico.repositorio.SubcategoriaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubcategoriaServico {

	private final ModelMapper modelMapper;
	private final SubcategoriaRepositorio subcategoriaRepositorio;
	private final CategoriaRepositorio categoriaRepositorio;
	

	public Subcategoria criarSubcategoria(SubcategoriaDtO subcategoriaDto,Long categoriaId) {	
		 var categoria = categoriaRepositorio.findById(categoriaId).orElseThrow();
		 var subcategoria = modelMapper.map(subcategoriaDto, Subcategoria.class);
		 subcategoria.setCategoria(categoria);
		 return subcategoriaRepositorio.save(subcategoria);
	}
	
	public Page<BuscarSubCategoriaDto > listarSubcategorias(Pageable pageable) {
		return subcategoriaRepositorio.findAll(pageable)
				.map(listar -> modelMapper
				.map(listar,BuscarSubCategoriaDto.class ));
	}
	
	public Subcategoria buscarPorId(@PathVariable Long id) {
		Optional<Subcategoria>buscar = subcategoriaRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Subcategoria atualizarSubcategoria(AtualizarSubDto atualizarDto,Long id) {
		atualizarDto.setId(id);
		return subcategoriaRepositorio.save(modelMapper.map(atualizarDto, Subcategoria.class));
	}
	
	public void excluirSubcategoria(Long id) {
		buscarPorId(id);
		subcategoriaRepositorio.deleteById(id);
	}
}

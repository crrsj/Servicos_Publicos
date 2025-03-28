package br.com.publico.servico;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}

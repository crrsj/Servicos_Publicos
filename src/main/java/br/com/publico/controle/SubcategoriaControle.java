package br.com.publico.controle;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.publico.dto.BuscarSubCategoriaDto;
import br.com.publico.dto.SubcategoriaDtO;
import br.com.publico.servico.SubcategoriaServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subcategoria")
@RequiredArgsConstructor
public class SubcategoriaControle {
	
	private final ModelMapper modelMapper;
	private final SubcategoriaServico subcategoriaServico;
	
	
	@PostMapping("/{categoriaId}")
	public ResponseEntity<SubcategoriaDtO>criarSubCategoria(@RequestBody SubcategoriaDtO subcategoriaDtO,
			                                                @PathVariable Long categoriaId) throws IOException, InterruptedException{
		var criar = subcategoriaServico.criarSubcategoria(subcategoriaDtO,categoriaId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, SubcategoriaDtO.class));
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<BuscarSubCategoriaDto>>listarSubcategorias(@RequestParam(defaultValue = "0")int pagina,
			                                                        @RequestParam(defaultValue = "10")int tamanho){
		var paginacao   = PageRequest.of(pagina, tamanho);
		List<BuscarSubCategoriaDto>listar = subcategoriaServico.listarSubcategorias(paginacao).getContent();
		return ResponseEntity.ok(listar);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<BuscarSubCategoriaDto>buscarPorId(@PathVariable Long id){
		var buscar = subcategoriaServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarSubCategoriaDto.class));
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void>excluirSubcategoria(@PathVariable Long id){
		subcategoriaServico.excluirSubcategoria(id);
		return ResponseEntity.noContent().build();
	}
}

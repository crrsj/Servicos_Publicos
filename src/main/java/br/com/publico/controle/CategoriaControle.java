package br.com.publico.controle;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.publico.dto.AtualizarDto;
import br.com.publico.dto.BuscarCategoriasDto;
import br.com.publico.dto.CategoriasDto;
import br.com.publico.servico.CategoriaServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaControle {
	
	private final ModelMapper modelMapper;
	private final CategoriaServico categoriaServico;
	
	@PostMapping
	public ResponseEntity<CategoriasDto>criarCategoria(@RequestBody CategoriasDto categoriasDto){
		var criar = categoriaServico.criarCategoria(categoriasDto);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, CategoriasDto.class));
	}
	
	
	@GetMapping
	public ResponseEntity<List<BuscarCategoriasDto>>listarCategorias(@RequestParam(defaultValue = "0")int pagina,
			                                                         @RequestParam(defaultValue = "10")int tamanho){
    var paginacao = PageRequest.of(pagina, tamanho);
    List<BuscarCategoriasDto>listar = categoriaServico.listarCategorias(paginacao).getContent();
    return ResponseEntity.ok(listar);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BuscarCategoriasDto>buscarPorId(@PathVariable Long id){
		var buscar = categoriaServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarCategoriasDto.class));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AtualizarDto>atualizarCategorias(@RequestBody AtualizarDto atualizarDto,@PathVariable Long id){
		var atualizar = categoriaServico.atualizarcategoria(atualizarDto, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarDto.class));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>excluirCategoria(@PathVariable Long id){
		categoriaServico.excluirCategoria(id);
		return ResponseEntity.noContent().build();
	}
	
}

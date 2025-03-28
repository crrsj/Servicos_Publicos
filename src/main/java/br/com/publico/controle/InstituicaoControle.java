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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.publico.dto.AtualizarInstDto;
import br.com.publico.dto.BuscarInstituicaoDto;
import br.com.publico.dto.InstituicaoDto;
import br.com.publico.servico.InstituicaoServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/instituicao")
@RequiredArgsConstructor
public class InstituicaoControle {
	
	private final ModelMapper modelMapper;
	private final InstituicaoServico instituicaoServico;
	
	@PostMapping("{subcategoriaId}")
	public ResponseEntity<InstituicaoDto>criarInstituicao(@RequestBody InstituicaoDto instituicaoDto,
			                                               @PathVariable Long subcategoriaId) throws IOException, InterruptedException{
		var criar = instituicaoServico.criarInstituicao(instituicaoDto, subcategoriaId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, InstituicaoDto.class));
		
	}
	
	@GetMapping
	public ResponseEntity<List<BuscarInstituicaoDto>>listarInstituicoes(@RequestParam(defaultValue = "0")int pagina,
			                                                            @RequestParam(defaultValue = "10")int tamanho){
		var paginacao = PageRequest.of(pagina, tamanho);
	    List<BuscarInstituicaoDto>listar = instituicaoServico.listarInstituicoes(paginacao).getContent();
	    return ResponseEntity.ok(listar);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BuscarInstituicaoDto>buscarPorId(@PathVariable Long id){
		var busca = instituicaoServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(busca, BuscarInstituicaoDto.class));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AtualizarInstDto>atualizarInstituicoes(@RequestBody AtualizarInstDto atualizarDto,
			                                                      @PathVariable Long id){
		var atualizar = instituicaoServico.atualizarInstituicao(atualizarDto, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarInstDto.class));
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>excluirInstituicoes(@PathVariable Long id){
		instituicaoServico.excluirInstituicao(id);
		return ResponseEntity.noContent().build();
	}
}

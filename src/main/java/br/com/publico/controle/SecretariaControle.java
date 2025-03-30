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

import br.com.publico.dto.AtualalizarsecDto;
import br.com.publico.dto.BuscarSecretariaDto;
import br.com.publico.dto.SecretariaDto;
import br.com.publico.servico.SecretariaServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/secretaria")
@RequiredArgsConstructor
public class SecretariaControle {
	
	private final ModelMapper modelMapper;
	private final SecretariaServico secretariaServico;
	
	
	@PostMapping
	public ResponseEntity<SecretariaDto>criarSecretaria(@RequestBody SecretariaDto secretariaDto) throws IOException, InterruptedException{
		var criar = secretariaServico.criarSecretaria(secretariaDto);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	    .buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, SecretariaDto.class));
	
	}   
	
	
	@GetMapping
	public ResponseEntity<List<BuscarSecretariaDto>>listarSecretarias(@RequestParam(defaultValue = "0")int pagina,
			                                                           @RequestParam(defaultValue = "10")int tamanho){
		var paginacao =  PageRequest.of(pagina, tamanho);
		List<BuscarSecretariaDto>listar = secretariaServico.listarSecretarias(paginacao).getContent();
		return ResponseEntity.ok(listar);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<BuscarSecretariaDto>buscarPorId(@PathVariable Long id){
		var buscar = secretariaServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarSecretariaDto.class));
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<AtualalizarsecDto>atualizarSecretaria(@RequestBody AtualalizarsecDto atualizarDto,
			                                                    @PathVariable Long id){
		var atualizar = secretariaServico.atualizarSecretaria(atualizarDto, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualalizarsecDto.class));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>excluirSecretaria(@PathVariable Long id){
		secretariaServico.excluirSecretaria(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/buscarNome")
	public ResponseEntity<SecretariaDto>buscarPorNome(@RequestParam(name = "nome")String nome){
		var buscar = secretariaServico.buscarPorNome(nome);
		return ResponseEntity.ok().body(modelMapper.map(buscar, SecretariaDto.class));
	}
	
	

}

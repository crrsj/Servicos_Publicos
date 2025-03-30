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

import br.com.publico.dto.AtualizarTelefoneDto;
import br.com.publico.dto.BuscarTelefoneUtil;
import br.com.publico.dto.TelefoneUtilDto;
import br.com.publico.servico.TelUtilServico;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/telefone")
@RequiredArgsConstructor
public class TelefoneUtilControle {

	private final ModelMapper modelMapper;
	private final TelUtilServico telUtilServico;
	
	
	@PostMapping
	public ResponseEntity<TelefoneUtilDto>criaraTelefone(@RequestBody TelefoneUtilDto telUtilDto){
		var criar = telUtilServico.criarTelefone(telUtilDto);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(criar.getId()).toUri();
		
		return ResponseEntity.created(uri).body(modelMapper.map(criar, TelefoneUtilDto.class));
	}
	
	
	@GetMapping
	public ResponseEntity<List<BuscarTelefoneUtil>>listarTelefones(@RequestParam(defaultValue = "0")int pagina,
			                                                       @RequestParam(defaultValue = "10")int tamanho){
	var	paginacao = PageRequest.of(pagina, tamanho);
	List<BuscarTelefoneUtil>listar = telUtilServico.listarTelefones(paginacao).getContent();
	return ResponseEntity.ok(listar);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<BuscarTelefoneUtil>buscarPorId(@PathVariable Long id){
		var buscar = telUtilServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarTelefoneUtil.class));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<TelefoneUtilDto>atualizarTelefones(@RequestBody AtualizarTelefoneDto dto,@PathVariable Long id) {
		var atualizar = telUtilServico.atualizarTelefone(dto, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar,TelefoneUtilDto.class ));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>excluirTelefones(@PathVariable Long id){
		telUtilServico.excluirTelefone(id);
		return ResponseEntity.noContent().build();
	}
}

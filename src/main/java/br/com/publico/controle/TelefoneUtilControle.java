package br.com.publico.controle;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/telefones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TelefoneUtilControle {

	private final ModelMapper modelMapper;
	private final TelUtilServico telUtilServico;
	
	
	@PostMapping
	@Operation(summary = "Endpoint responsável por cadastrar telefones.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<TelefoneUtilDto>criaraTelefone(@RequestBody @Valid TelefoneUtilDto telUtilDto){
		var criar = telUtilServico.criarTelefone(telUtilDto);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(criar.getId()).toUri();
		
		return ResponseEntity.created(uri).body(modelMapper.map(criar, TelefoneUtilDto.class));
	}
	
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar todos os telefones(contém paginação).") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<List<BuscarTelefoneUtil>>listarTelefones(@RequestParam(defaultValue = "0")int pagina,
			                                                       @RequestParam(defaultValue = "10")int tamanho){
	var	paginacao = PageRequest.of(pagina, tamanho);
	List<BuscarTelefoneUtil>listar = telUtilServico.listarTelefones(paginacao).getContent();
	return ResponseEntity.ok(listar);
		
	}
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar telefone pelo ID.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<BuscarTelefoneUtil>buscarPorId(@PathVariable Long id){
		var buscar = telUtilServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarTelefoneUtil.class));
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar telefone pelo ID.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<TelefoneUtilDto>atualizarTelefones(@RequestBody @Valid AtualizarTelefoneDto dto,@PathVariable Long id) {
		var atualizar = telUtilServico.atualizarTelefone(dto, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar,TelefoneUtilDto.class ));
	}
	
	
	@GetMapping("/buscarNome")
	@Operation(summary = "Endpoint responsável por buscar telefone pelo nome da entidade.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<BuscarTelefoneUtil>buscarPorNome(@RequestParam("nome")String nome){
		var buscar = telUtilServico.buscarPorNome(nome);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarTelefoneUtil.class));
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por deletar telefones.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<Void>excluirTelefones(@PathVariable Long id){
		telUtilServico.excluirTelefone(id);
		return ResponseEntity.noContent().build();
	}
}

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/instituicao")
@RequiredArgsConstructor
public class InstituicaoControle {
	
	private final ModelMapper modelMapper;
	private final InstituicaoServico instituicaoServico;
	
	@PostMapping("{subcategoriaId}")
	@Operation(summary = "Endpoint responsável por cadastrar instuições,passando o ID da subcategoria como parâmetro.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<InstituicaoDto>criarInstituicao(@RequestBody InstituicaoDto instituicaoDto,
			                                               @PathVariable Long subcategoriaId) throws IOException, InterruptedException{
		var criar = instituicaoServico.criarInstituicao(instituicaoDto, subcategoriaId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, InstituicaoDto.class));
		
	}
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar todas as instituições.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<List<BuscarInstituicaoDto>>listarInstituicoes(@RequestParam(defaultValue = "0")int pagina,
			                                                            @RequestParam(defaultValue = "10")int tamanho){
		var paginacao = PageRequest.of(pagina, tamanho);
	    List<BuscarInstituicaoDto>listar = instituicaoServico.listarInstituicoes(paginacao).getContent();
	    return ResponseEntity.ok(listar);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar instituiçãao pelo ID.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<BuscarInstituicaoDto>buscarPorId(@PathVariable Long id){
		var busca = instituicaoServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(busca, BuscarInstituicaoDto.class));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar instituição pelo ID.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<AtualizarInstDto>atualizarInstituicoes(@RequestBody AtualizarInstDto atualizarDto,
			                                                      @PathVariable Long id){
		var atualizar = instituicaoServico.atualizarInstituicao(atualizarDto, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarInstDto.class));
				
	}
	
	
	@GetMapping("/buscaBairro")
	@Operation(summary = "Endpoint responsável por buscar bairro visando encontrar instituição mais próxima.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<List<BuscarInstituicaoDto>>buscarPorBairro(@RequestParam(name ="bairro")String bairro){
		var buscarBairro = instituicaoServico.buscarPorBairro(bairro)
				.stream().map(listar -> modelMapper						
				.map(listar, BuscarInstituicaoDto.class)).toList();
		return ResponseEntity.ok(buscarBairro);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por deletar instituição pelo ID.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<Void>excluirInstituicoes(@PathVariable Long id){
		instituicaoServico.excluirInstituicao(id);
		return ResponseEntity.noContent().build();
	}
	
}

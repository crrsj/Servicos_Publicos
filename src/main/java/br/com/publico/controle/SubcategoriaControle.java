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

import br.com.publico.dto.AtualizarSubDto;
import br.com.publico.dto.BuscarSubCategoriaDto;
import br.com.publico.dto.SubcategoriaDtO;
import br.com.publico.servico.SubcategoriaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/subcategorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SubcategoriaControle {
	
	private final ModelMapper modelMapper;
	private final SubcategoriaServico subcategoriaServico;
	
	
	@PostMapping("/{categoriaId}")
	@Operation(summary = "Endpoint responsável por cadastrar Subcategorias passando o ID da categoria como parâmetro.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<SubcategoriaDtO>criarSubCategoria(@RequestBody @Valid SubcategoriaDtO subcategoriaDtO,
			                                                @PathVariable Long categoriaId){
		var criar = subcategoriaServico.criarSubcategoria(subcategoriaDtO,categoriaId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, SubcategoriaDtO.class));
		
	}
	
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar todas as subcategorias(contém paginação).") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<List<BuscarSubCategoriaDto>>listarSubcategorias(@RequestParam(defaultValue = "0")int pagina,
			                                                        @RequestParam(defaultValue = "10")int tamanho){
		var paginacao = PageRequest.of(pagina, tamanho);
		List<BuscarSubCategoriaDto>listar = subcategoriaServico.listarSubcategorias(paginacao).getContent();
		return ResponseEntity.ok(listar);
	}
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar subcategorias pelo ID.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<BuscarSubCategoriaDto>buscarPorId(@PathVariable Long id){
		var buscar = subcategoriaServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarSubCategoriaDto.class));
	}
	

	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por deletar subcategorias pelo ID.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<Void>excluirSubcategoria(@PathVariable Long id){
		subcategoriaServico.excluirSubcategoria(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/buscarSubcategoria")
	@Operation(summary = "Endpoint responsável por buscar subcategorias pelo nome.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<BuscarSubCategoriaDto>buscarNome(@RequestParam("nomeSubcategoria")String nomeSubcategoria){
		var buscar = subcategoriaServico.buscarSubCategoria(nomeSubcategoria);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarSubCategoriaDto.class));
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar subcategorias pelo ID.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<AtualizarSubDto>atualizarCategoria(@RequestBody @Valid AtualizarSubDto DTO,
			                                                  @PathVariable Long id){
		var atualizar  = subcategoriaServico.atualizarSubcategoria(DTO, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarSubDto.class));
	}
	
	@GetMapping("/buscarSub")
	@Operation(summary = "Endpoint responsável por buscar subcategoria pelo nome.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<BuscarSubCategoriaDto>buscarNomeSubcategoria(@RequestParam(name = "nomeSubcategoria")String nomeSubcategoria){
		var buscar = subcategoriaServico.buscarPorNomeSubcategoria(nomeSubcategoria);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarSubCategoriaDto.class));
	}
}

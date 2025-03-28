package br.com.publico.controle;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

}

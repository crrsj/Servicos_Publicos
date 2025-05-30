package br.com.publico.servico;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.publico.dto.AtualizarInstDto;
import br.com.publico.dto.BuscarInstituicaoDto;
import br.com.publico.dto.InstituicaoDto;
import br.com.publico.entidade.Instituicao;
import br.com.publico.repositorio.InstituicaoRepositorio;
import br.com.publico.repositorio.SubcategoriaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstituicaoServico {
	
	private final ModelMapper modelMapper;
	private final SubcategoriaRepositorio subcategoriaRepositorio;
	private final InstituicaoRepositorio instituicaoRepositorio;
	private final HttpClient client;
	private final Gson gson;

	public Instituicao criarInstituicao(InstituicaoDto instituicaoDto,Long subcategoriaId) throws IOException, InterruptedException {
	        	        
		        String url = "https://viacep.com.br/ws/" + instituicaoDto.getCep() + "/json/";
		        HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create(url))
		                .header("Accept", "application/json")
		                .GET()
		                .build();

		        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

		        if (response.statusCode() != 200) {
		            throw new IllegalArgumentException("Erro na requisição: Código " + response.statusCode());
		        }

		        
		        var cadastroCep = gson.fromJson(response.body(), InstituicaoDto.class);
		    	var subcategoria = subcategoriaRepositorio.findById(subcategoriaId).orElseThrow();
		        var instituicao = modelMapper.map(instituicaoDto, Instituicao.class);
	            instituicao.setSubcategoria(subcategoria);     
		      
		        instituicao.setLogradouro(cadastroCep.getLogradouro());
		        instituicao.setBairro(cadastroCep.getBairro());
		        instituicao.setLocalidade(cadastroCep.getLocalidade());
		        instituicao.setEstado(cadastroCep.getEstado());
		        instituicao.setCep(cadastroCep.getCep());	     
		     
		        		        
		        return instituicaoRepositorio.save(instituicao);
	}
	
	
	public Page<BuscarInstituicaoDto>listarInstituicoes(Pageable pageable){
		return instituicaoRepositorio.findAll(pageable) 
				.map(listar ->modelMapper.map(listar, BuscarInstituicaoDto.class));
	}

	public Instituicao buscarPorId(Long id) {
		Optional<Instituicao>buscar = instituicaoRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Instituicao atualizarInstituicao(AtualizarInstDto atualizarDto,Long id) {
		atualizarDto.setId(id);
		return instituicaoRepositorio.save(modelMapper.map(atualizarDto, Instituicao.class));
	}
	
	
	  public List<Instituicao> buscarPorBairro(String bairro){ 
		  	       
	        return instituicaoRepositorio.findByBairro(bairro.trim().toUpperCase());
	        		
	           
	    }
			
	
	public void excluirInstituicao(Long id) {
		buscarPorId(id);
		instituicaoRepositorio.deleteById(id);
	}
}

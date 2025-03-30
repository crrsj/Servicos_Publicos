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

import br.com.publico.dto.AtualalizarsecDto;
import br.com.publico.dto.BuscarSecretariaDto;
import br.com.publico.dto.SecretariaDto;
import br.com.publico.entidade.Secretaria;
import br.com.publico.repositorio.SecretariaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecretariaServico {
	
	private final ModelMapper modelMapper;
	private final SecretariaRepositorio secretariaRepositorio;
	private final HttpClient client;
	private final Gson gson;
	

	public Secretaria criarSecretaria(SecretariaDto secretariaDto) throws IOException, InterruptedException {		
		
        String url = "https://viacep.com.br/ws/" + secretariaDto.getCep() + "/json/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na requisição: Código " + response.statusCode());        }

        var cadastroCep = gson.fromJson(response.body(), SecretariaDto.class);           

        var criarSecretaria  = modelMapper.map(secretariaDto, Secretaria.class);
        
        criarSecretaria.setLogradouro(cadastroCep.getLogradouro());
        criarSecretaria.setBairro(cadastroCep.getBairro());
        criarSecretaria.setLocalidade(cadastroCep.getLocalidade());
        criarSecretaria.setEstado(cadastroCep.getEstado());
        criarSecretaria.setCep(cadastroCep.getCep());       
                
        return secretariaRepositorio.save(criarSecretaria);
   }	
	
	
	
	public Page<BuscarSecretariaDto>listarSecretarias(Pageable pageable) {
		return secretariaRepositorio.findAll(pageable)
				.map(listar -> modelMapper
				.map(listar,BuscarSecretariaDto.class));
	}
		
	public Secretaria buscarPorId(Long id) {
		Optional<Secretaria>buscar = secretariaRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Secretaria atualizarSecretaria(AtualalizarsecDto atualizarDto,Long id) {
		atualizarDto.setId(id);
		return secretariaRepositorio.save(modelMapper.map(atualizarDto, Secretaria.class));
	}
	
	public void excluirSecretaria(Long id) {
		buscarPorId(id);
		secretariaRepositorio.deleteById(id);
	}
	
	public List<BuscarSecretariaDto> buscarPorNome(String nome) {
		return secretariaRepositorio.findByNome(nome.trim().toUpperCase());
		
	}
	  
}

  


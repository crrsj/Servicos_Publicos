package br.com.publico.dto;

import java.util.List;

import br.com.publico.entidade.Categoria;
import br.com.publico.entidade.Instituicao;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubcategoriaDtO {
	@NotBlank(message = "não pode estar em branco !")
	private String nomeSubcategoria;	
	private Categoria categoria;	
	private List<Instituicao>instituicoes;

}

package br.com.publico.dto;

import java.util.List;

import br.com.publico.entidade.Categoria;
import br.com.publico.entidade.Instituicao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarSubCategoriaDto {
	private Long id;
	private String nomeSubcategoria;
	private Categoria categoria;	
	private List<Instituicao>instituicoes;

}

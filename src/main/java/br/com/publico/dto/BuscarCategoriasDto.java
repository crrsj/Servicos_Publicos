package br.com.publico.dto;

import java.util.List;

import br.com.publico.entidade.Subcategoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarCategoriasDto {

	private Long id;
	private String nome;	
    private List<Subcategoria>subcategorias;
}

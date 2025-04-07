package br.com.publico.dto;

import java.util.List;

import br.com.publico.entidade.Subcategoria;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarDto {

	private Long id;
	@NotBlank(message = "não pode estar em branco !")
	private String nome;
	private List<Subcategoria> subcategoria;	

}

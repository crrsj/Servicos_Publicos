package br.com.publico.dto;

import br.com.publico.entidade.Subcategoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarInstituicaoDto {

	private Long id;
	private String nome;	
	private String telefone;
	private String email;
	private String cep;
	private String logradouro;	
	private String numero;
	private String bairro;
	private String localidade;
	private String estado;
	private Subcategoria subcategoria;
}

package br.com.publico.dto;

import br.com.publico.entidade.Subcategoria;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstituicaoDto {
	@NotBlank(message = "não pode estar em branco !")
	private String nome;	
	@NotBlank(message = "não pode estar em branco !")
	private String telefone;	
	private String email;
	@NotBlank(message = "não pode estar em branco !")
	private String cep;
	@NotBlank(message = "não pode estar em branco !")
	private String logradouro;	
	@NotBlank(message = "não pode estar em branco !")
	private String numero;
	@NotBlank(message = "não pode estar em branco !")
	private String bairro;
	@NotBlank(message = "não pode estar em branco !")
	private String localidade;
	@NotBlank(message = "não pode estar em branco !")
	private String estado;
	private Subcategoria subcategoria;
}

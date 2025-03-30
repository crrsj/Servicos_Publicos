package br.com.publico.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SecretariaDto {

	private String nome;	
	private String telefone;
	private String cep;
	private String logradouro;	
	private String numero;
	private String bairro;
	private String localidade;
	private String estado;	
	

	
}

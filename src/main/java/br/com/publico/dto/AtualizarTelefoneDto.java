package br.com.publico.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarTelefoneDto {

	private Long id;
	private String nome;
	private String numero;
}

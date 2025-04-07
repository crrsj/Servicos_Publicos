package br.com.publico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelefoneUtilDto {
	@NotBlank(message = "não pode estar em branco !")
	private String nome;
	@NotBlank(message = "não pode estar em branco !")
	private String numero;
}

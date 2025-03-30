package br.com.publico.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarTelefoneUtil {

	private Long id;
	private String nome;
	private String numero;
}

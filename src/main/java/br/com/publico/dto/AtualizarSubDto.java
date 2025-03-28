package br.com.publico.dto;

import java.util.List;

import br.com.publico.entidade.Categoria;
import br.com.publico.entidade.Instituicao;
import br.com.publico.entidade.Secretaria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarSubDto {
	private Long id;
	private String nome;	
	private Secretaria secretaria;	
	private Categoria categoria;	
	private List<Instituicao>instituicoes;
}

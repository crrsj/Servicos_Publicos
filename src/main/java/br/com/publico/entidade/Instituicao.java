package br.com.publico.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "tb_instituicoes")
@Data
@NoArgsConstructor
public class Instituicao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne
	@JoinColumn(name = "subcategoria_id")
	private Subcategoria subcategoria;
	
}

package br.com.publico.entidade;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_secretarias")
@Data
@NoArgsConstructor
public class Secretaria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;	
	private String telefone;
	private String cep;
	private String logradouro;
	private String complemento;
	private String numero;
	private String bairro;
	private String localidade;
	private String estado;
	@JsonIgnore
	@OneToMany(mappedBy = "secretaria", cascade = CascadeType.ALL)
	private List<Subcategoria> subcategorias;
	

}

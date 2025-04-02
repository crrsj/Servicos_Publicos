package br.com.publico.entidade;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_subcategorias")
public class Subcategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nomeSubcategoria;
	@ManyToOne	
	@JoinColumn(name = "categor_id")
	private Categoria categoria;
	@JsonIgnore
	@OneToMany(mappedBy = "subcategoria", cascade = CascadeType.ALL)
	private List<Instituicao>instituicoes;
} 

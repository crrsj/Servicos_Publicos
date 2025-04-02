package br.com.publico.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.publico.entidade.Subcategoria;

public interface SubcategoriaRepositorio extends JpaRepository<Subcategoria, Long>{
	
	@Query("SELECT s FROM Subcategoria s WHERE UPPER(s.nomeSubcategoria) = UPPER(:nomeSubcategoria)")
	Optional<Subcategoria> findBySubCategoria(String nomeSubcategoria);

}

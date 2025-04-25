package br.com.publico.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.publico.entidade.Subcategoria;

public interface SubcategoriaRepositorio extends JpaRepository<Subcategoria, Long>{
	
	//@Query("SELECT s FROM Subcategoria s WHERE UPPER(s.nomeSubcategoria) = UPPER(:nomeSubcategoria)")
	@Query("""
		    SELECT s FROM Subcategoria s 
		    WHERE 
		        TRANSLATE(UPPER(REPLACE(s.nomeSubcategoria, ' ', '')), 
		                  'ÁÉÍÓÚÂÊÎÔÛÃÕÀÇ', 
		                  'AEIOUAEIOUAOC') = 
		        TRANSLATE(UPPER(REPLACE(:nomeSubcategoria, ' ', '')), 
		                  'ÁÉÍÓÚÂÊÎÔÛÃÕÀÇ', 
		                  'AEIOUAEIOUAOC')
		""")
	Optional<Subcategoria> findBySubCategoria(String nomeSubcategoria);

}

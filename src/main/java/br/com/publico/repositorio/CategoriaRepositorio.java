package br.com.publico.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.publico.entidade.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

	@Query("SELECT c FROM Categoria c WHERE UPPER(c.nome) = UPPER(:nome)")
	Optional<Categoria> findByNome(@Param("nome") String categoria);

	
}

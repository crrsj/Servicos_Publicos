package br.com.publico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.publico.entidade.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

	
}

package br.com.publico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.publico.entidade.Instituicao;

public interface InstituicaoRepositorio extends JpaRepository<Instituicao, Long> {

	@Query(value = "select i from Instituicao i where upper(trim(i.bairro)) like %?1% ") 
	List<Instituicao> findByBairro(@Param("bairro") String bairro);

}

package br.com.publico.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.publico.dto.BuscarSecretariaDto;
import br.com.publico.entidade.Secretaria;

public interface SecretariaRepositorio extends JpaRepository<Secretaria, Long> {

	 @Query("SELECT s FROM Secretaria s WHERE LOWER(s.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	 List<BuscarSecretariaDto> findByNome(@Param("nome")String nome);
	 
	 @Query("SELECT s FROM Secretaria s WHERE LOWER(s.bairro) LIKE LOWER(CONCAT('%', :bairro, '%'))")
	 Optional<Secretaria> findByBairro(String bairro);
		
	

}

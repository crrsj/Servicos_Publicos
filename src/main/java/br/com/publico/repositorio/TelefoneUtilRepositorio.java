package br.com.publico.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.publico.entidade.TelefoneUtil;

public interface TelefoneUtilRepositorio extends JpaRepository<TelefoneUtil, Long> {

  @Query("SELECT t FROM TelefoneUtil t WHERE UPPER(t.nome) = UPPER(:nome)")	
  Optional<TelefoneUtil> findByNome(@Param("nome") String nome);

	}

	



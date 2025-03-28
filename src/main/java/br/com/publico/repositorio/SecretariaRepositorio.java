package br.com.publico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.publico.entidade.Secretaria;

public interface SecretariaRepositorio extends JpaRepository<Secretaria, Long> {

}

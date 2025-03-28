package br.com.publico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.publico.entidade.Instituicao;

public interface InstituicaoRepositorio extends JpaRepository<Instituicao, Long> {

}

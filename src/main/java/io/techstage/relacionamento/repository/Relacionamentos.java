package io.techstage.relacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.techstage.relacionamento.model.Relacionamento;

public interface Relacionamentos extends JpaRepository<Relacionamento, Long>  {
  
}
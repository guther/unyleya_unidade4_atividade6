package com.example.atividade6.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.atividade6.model.Cidadao;

public interface CidadaoRepository extends JpaRepository<Cidadao, Integer> {
	
}
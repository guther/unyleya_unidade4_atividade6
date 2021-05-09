package com.example.atividade6.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.atividade6.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
	
}
package com.example.atividade6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.atividade6.model.Genero;
import com.example.atividade6.repository.GeneroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeneroService {
	@Autowired
	private GeneroRepository generoRepository;
	
	public List<Genero> findAll(){
		return generoRepository.findAll();
	}
	
	public Optional<Genero> findById(Integer id) {
		return generoRepository.findById(id);
	}

	public Map<String, Object> save(Genero genero) {
		try {
			ObjectMapper oMapper = new ObjectMapper();
			Map<String, Object> map = oMapper.convertValue(generoRepository.save(genero), Map.class);
			return map;
		}
		catch(DataIntegrityViolationException e) {
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("msg", "Já existe um gênero cadastrado com essa descriçao.");
			return map2;
		}
	}
	
	public Map<String, Object> deleteById(Integer id) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			generoRepository.deleteById(id);
			map.put("msg", "success");
		}
		catch(EmptyResultDataAccessException e) {
			map.put("msg", "Não existe gêneros com id="+id);
		}
		catch(DataIntegrityViolationException e) {
			map.put("msg", "Não foi possível remover, pois existe cidadãos cadastrados com esse gênero.");
		}
		return map;
	}
}
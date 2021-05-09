package com.example.atividade6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.atividade6.model.Cidadao;
import com.example.atividade6.repository.CidadaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CidadaoService {
	@Autowired
	private CidadaoRepository cidadaoRepository;
	
	public List<Cidadao> findAll(){
		return cidadaoRepository.findAll();
	}
	
	public Optional<Cidadao> findById(Integer id) {
		return cidadaoRepository.findById(id);
	}

	public Map<String, Object> save(Cidadao cidadao) {
		try {
			ObjectMapper oMapper = new ObjectMapper();
			Map<String, Object> map = oMapper.convertValue(cidadaoRepository.save(cidadao), Map.class);
			return map;
		}
		catch(DataIntegrityViolationException e) {
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("msg", e.getMessage());
			return map2;
		}
	}
	
	public Map<String, Object> deleteById(Integer id) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			cidadaoRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			map.put("msg", e.getMessage());
			return map;
		}
	    map.put("msg", "success");
		return map;
	}
}

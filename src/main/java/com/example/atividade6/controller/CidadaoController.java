package com.example.atividade6.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.atividade6.model.Cidadao;
import com.example.atividade6.model.Genero;
import com.example.atividade6.service.CidadaoService;
import com.example.atividade6.service.GeneroService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1")
public class CidadaoController {

	@Autowired
	private CidadaoService cidadaoService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping(value = "/cidadaos", produces = "application/json")
	public List<Cidadao> findAll(){
		return cidadaoService.findAll();
	}
	
	@GetMapping(value = "/cidadao/{id}")
	public Cidadao findById(@PathVariable Integer id){
		Optional<Cidadao> editing_cidadao = cidadaoService.findById(id);
		if(editing_cidadao.isPresent()) {
			return editing_cidadao.get();
		}
		return new Cidadao();
	}
	
	@PostMapping(value = "/cidadao", consumes = "application/json", produces = "application/json")
	public Map<String, Object> save(@RequestBody Cidadao cidadao){
		if(cidadao != null) {	
			Genero genero = cidadao.getGenero();
			Optional<Genero> chosen_genero = generoService.findById(genero.getId());
			if(chosen_genero.isPresent()) {
				cidadao.setGenero(chosen_genero.get());
				return cidadaoService.save(cidadao);
			}
			else{
				HashMap<String, Object> error = new HashMap<>();
				error.put("msg", "O gênero escolhido não existe na base de dados.");
				return error;
			}
		}
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> map = oMapper.convertValue(new Cidadao(), Map.class);
		return map;
	}
	
	@PutMapping(value = "/cidadao", consumes = "application/json", produces = "application/json")
	public Map<String, Object> editCidadao(@RequestBody Cidadao cidadao){
		if(cidadao != null) {	
			Genero genero = cidadao.getGenero();
			Optional<Genero> chosen_genero = generoService.findById(genero.getId());
			if(chosen_genero.isPresent()) {
				cidadao.setGenero(chosen_genero.get());
				return cidadaoService.save(cidadao);
			}
			else{
				HashMap<String, Object> error = new HashMap<>();
				error.put("msg", "O gênero escolhido não existe na base de dados.");
				return error;
			}
		}
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> map = oMapper.convertValue(new Cidadao(), Map.class);
		return map;
	}
	
	@DeleteMapping(value = "/cidadao/{id}", produces = "application/json")
	public Map<String, Object> deleteById(@PathVariable Integer id){
		return cidadaoService.deleteById(id);
	}
}

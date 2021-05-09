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

import com.example.atividade6.model.Genero;
import com.example.atividade6.service.GeneroService;

@RestController
@RequestMapping("/api/v1")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping(value = "/generos", produces = "application/json")
	public List<Genero> findAll(){
		return generoService.findAll();
	}
	
	@GetMapping(value = "/genero/{id}")
	public Genero findById(@PathVariable Integer id){
		Optional<Genero> editing_genero = generoService.findById(id);
		if(editing_genero.isPresent()) {
			return editing_genero.get();
		}
		return new Genero();
	}
	
	@PostMapping(value = "/genero", consumes = "application/json", produces = "application/json")
	public Map<String, Object> save(@RequestBody Genero genero){
		return generoService.save(genero);
	}
	
	@PutMapping(value = "/genero", consumes = "application/json", produces = "application/json")
	public Map<String, Object> editGenero(@RequestBody Genero genero){
		return generoService.save(genero);
	}
	
	@DeleteMapping(value = "/genero/{id}", produces = "application/json")
	public Map<String, Object> deleteById(@PathVariable Integer id){
		return generoService.deleteById(id);
	}
}

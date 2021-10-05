package com.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.DTO.DetalhesDTO;
import com.academico.model.Agencia;
import com.academico.repository.AgenciaRepository;
import com.academico.service.AgenciaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/agencia")
public class AgenciaController {
	
	@Autowired
	AgenciaService agenciaService;
	AgenciaRepository agenciaRepository;
	
	@ApiOperation(value="Endpoint para cadastrar agência")
	@PostMapping("/post")
	public void salvarAgencia(@RequestBody Agencia agencia){
		this.agenciaService.salvarAgencia(agencia);
	}
	
	@ApiOperation(value="Endpoint para buscar todas as agências")
	@GetMapping("/get")
	public List<Agencia> buscarAgencia() {
		return this.agenciaService.buscarAgencias();
	}
	
	@ApiOperation(value="Endpoint para buscar agência por ID")
	@GetMapping("/{id}")
	public Agencia buscaAgencia(@PathVariable("id") String id) throws Exception {
		return this.agenciaService.buscaAgencia(id);
	}
	
	@ApiOperation(value="Endpoint para atualizar agência")
	@PutMapping("/att/{id}")
	public void alterarAgencia (@PathVariable("id") Long id, @RequestBody Agencia agencia) throws Exception {
		this.agenciaService.atualizarAgencia(agencia, id);
	}
	
	@ApiOperation(value="Endpoint para deletar agência")
	@DeleteMapping("/delete/{id}")
	public DetalhesDTO delete (@PathVariable("id") String id) throws Exception {
		return this.agenciaService.delete(id);
	 
	}
}

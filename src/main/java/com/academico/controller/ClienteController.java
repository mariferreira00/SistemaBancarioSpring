package com.academico.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.academico.model.Cliente;
import com.academico.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	
	@ApiOperation(value="Endpoint para buscar todos os clientes")
	@GetMapping("/get")
	public List<Cliente> buscarClientes() {
		return this.clienteService.buscarClientes();
	}
	
	@ApiOperation(value="Endpoint para buscar cliente por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarCliente( @PathVariable Long id) {
		Cliente cli = clienteService.buscarCliente(id);
		return ResponseEntity.ok().body(cli);
	}
	
	
	@ApiOperation(value="Endpoint para atualizar cliente")
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cliente) {
		clienteService.salvarCliente(cliente);
        return ResponseEntity.ok().body(cliente);
	}
	

	@ApiOperation(value = "Endpoint para deletar cliente")
	@DeleteMapping("/{id}")
	public String excluirCliente(@PathVariable("id") Long id) {
		this.clienteService.deletar(id);

		return "Cliente ID: " + id + " foi deletado com sucesso!";
	}

}
package com.academico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.academico.model.Cliente;
import com.academico.repository.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;



	
	public void salvarCliente(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}
	
	//metodo para buscar todos os clientes
	public List<Cliente> buscarClientes() {
		return this.clienteRepository.findAll();
	}
	
	//metodo buscar o cliente por id (utilizando uma sintaxe alternativa para tratamento de exceção 
	//com Response Status que retorna o status do cod de erro http além de msg personalizada
	public Cliente buscarCliente(Long id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não localizado!"));
	}
	
	//metodo para atualizar os dados do cliente
	public void atualizarCliente(Cliente cliente, Long id) throws Exception {
		Cliente clienteAtt = this.clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não localizado!"));
		cliente.setId(id);
		BeanUtils.copyProperties(cliente, clienteAtt, "id");
		this.salvarCliente(clienteAtt);

	}
	
	//metodo para deletar cliente
	public void deletar(Long id) {
		this.clienteRepository.deleteById(id);
	}
}
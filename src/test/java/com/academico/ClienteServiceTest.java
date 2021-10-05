package com.academico;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.academico.model.Cliente;
import com.academico.repository.ClienteRepository;
import com.academico.service.ClienteService;

@SpringBootTest
@RunWith(SpringRunner.class)
class ClienteServiceTest {

	static class ContaServiceConfiguration {

		@Bean
		public ClienteService clienteService() {
			return new ClienteService();
		}
	}

	@Autowired
	ClienteService clienteService;

	@MockBean
	ClienteRepository clienteRepository;

	@BeforeEach // Mock do dados retornados
	public void setUp() {
		Cliente cliente = new Cliente(null, null, null, null);
		cliente.setId(1L);
		cliente.setNome("Maria");
		cliente.setCpf("102.685.654-98");
		cliente.setTelefone("81997278585");

		Mockito.when(clienteRepository.findById(cliente.getId())).thenReturn(java.util.Optional.of(cliente));

	}

	@Test // Teste do método
	public void whenValidId_busca() {

		List<Cliente> found = clienteService.buscarClientes();

		Assertions.assertEquals(found, clienteRepository.findAll());
	}

}
package com.academico.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.DTO.OperacaoDTO;
import com.academico.DTO.OperacaoRealizadaDTO;
import com.academico.DTO.SaldoDTO;
import com.academico.DTO.TransferenciaDTO;
import com.academico.model.ContaCorrente;
import com.academico.model.Extrato;
import com.academico.service.ContaCorrenteService;

import io.swagger.annotations.ApiOperation;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/conta")
public class ContaCorrenteController {
	
	@Autowired
	ContaCorrenteService service;
	
	//Lista todas as contas
	@ApiOperation(value="Endpoint para buscar todas as contas")
	@GetMapping("/get")
	public List<ContaCorrente> buscarContaCorrente() {
		return this.service.buscarContaCorrente();
	}

	//Retorna os extratos de uma conta pelo ID
	@ApiOperation(value="Endpoint para buscar extrato por ID da conta")
	@GetMapping("/extrato/{id}")
	public ResponseEntity<?> mostrarExtrato(@PathVariable("id") String id) {
		List<Extrato> extratos = service.extratosConta(id);
		return ResponseEntity.ok().body(extratos);

	}

	//Cria uma conta
	@ApiOperation(value="Endpoint para criar uma conta")
	@PostMapping("/post")
	public void cadastrarConta(@RequestBody ContaCorrente contaCorrente) throws Exception {
		this.service.cadastroConta(contaCorrente);
	} 
	
	 //busca conta por id
	@ApiOperation(value="Endpoint para buscar conta por ID")
	@GetMapping("/get/{id}")
	public ContaCorrente buscaAgencia(@PathVariable("id") String numero) throws Exception {
		return this.service.buscarConta(numero);
	}

	//atualiza o saldo da conta pelo campo "valor"
	@ApiOperation(value="Endpoint para atualizar saldo da conta por ID")
	@PutMapping("/attsaldo/{id}")
	public ResponseEntity<?> atualizaSaldo(@PathVariable String id, @RequestBody SaldoDTO valor) throws Exception {
		ContaCorrente cc = service.buscarConta(id);
		service.attSaldo(cc, valor.getValor());
		return ResponseEntity.ok().body(cc);
	}

   //Transferencia entre contas
	@ApiOperation(value="Endpoint para transferência entre contas")
	@PostMapping("/transf")
	public ResponseEntity<?> transferencia(@RequestBody TransferenciaDTO transf) throws Exception {
		service.transferencia(transf);
		String msg = "Transferência realizada com sucesso!";
		return ResponseEntity.ok().body(msg);

	}
	
	/*@PostMapping("/transf")
	public String transferencia(@RequestBody TransferenciaDTO transf) throws Exception {
		service.transferencia(transf);
		
		return "Transferência Realizada!";

	}*/
	
	//endpoint para operações entre contas ( 1 = saque e 2 = deposito)
	@ApiOperation(value="Endpoint para Realizar uma operação por tipo, use 1 para SAQUE e 2 para DEPÓSITO")
	@PostMapping("/operacao")
	public ResponseEntity<?> operacao(@RequestBody OperacaoDTO dto) throws Exception {
		OperacaoRealizadaDTO oper = service.operacao(dto);
		
		return ResponseEntity.ok().body(oper);

	}
}

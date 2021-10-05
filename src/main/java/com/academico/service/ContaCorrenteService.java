package com.academico.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.academico.DTO.OperacaoDTO;
import com.academico.DTO.OperacaoRealizadaDTO;
import com.academico.DTO.TransferenciaDTO;
import com.academico.model.ContaCorrente;
import com.academico.model.Extrato;
import com.academico.model.OperacaoTipo;
import com.academico.repository.ClienteRepository;
import com.academico.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {
	
	@Autowired
	ContaCorrenteRepository contaRepository;

	@Autowired
	ExtratoService extratoService;

	@Autowired
	ClienteRepository clienteRepository;

	//metodo para criar uma conta
	public void cadastroConta(ContaCorrente conta) throws Exception {

		if (conta.getNumero() == null) {
			throw new Exception("Erro ao cadastrar, numero da conta não pode estar vazio");
		}

		if (contaRepository.findByNumero(conta.getNumero()) != null) {
			throw new Exception("Número da conta ja existe!");
		}
		contaRepository.save(conta);
	}
	
	//metodo para listar as contas
	public List<ContaCorrente> buscarContaCorrente() {
		return this.contaRepository.findAll();
	}

	
	//metodo para transferencia entre contas
	public void transferencia(TransferenciaDTO transf) throws Exception {

		BigDecimal c = new BigDecimal(0);

		if (!contaRepository.existsById(transf.getIdConta())) {
			throw new Exception("ID da conta de origem não existe no banco de dados");
		}

		ContaCorrente cc = contaRepository.findById(transf.getIdConta()).orElseThrow();

		if (transf.getContaDestino() == null) {
			throw new Exception("CAMPO OBRIGATÓRIO: contaDestino");
		}
		
		if (transf.getAgenciaDestino() == null) {
			throw new Exception("CAMPO OBRIGATÓRIO: agenciaDestino");
		}
		ContaCorrente contadestino = contaRepository.getContaTransferencia(transf.getContaDestino(),
				transf.getAgenciaDestino());

		if (contadestino == null) {
			throw new Exception("A conta destino não foi encontrada, favor informe os parametros corretos");
		}

		if (transf.getValor().compareTo(cc.getSaldo()) == 1) {
			throw new Exception("OPERAÇÃO NÃO REALIZADA! O valor ultrapassa o saldo da conta de origem!");
		}

		if (transf.getValor().compareTo(c) <= 0) {
			throw new Exception("Valor da transferência deve ser maior que 0");
		}

		cc.setSaldo(cc.getSaldo().subtract(transf.getValor()));
		contadestino.setSaldo(contadestino.getSaldo().add(transf.getValor(), MathContext.DECIMAL32));

		Extrato ext1 = new Extrato();
		ext1.setConta(cc);
		ext1.setDataHoraMovimento(null);
		ext1.setOperacaoTipo(OperacaoTipo.TRANSFERENCIAENV.getDescricao());
		ext1.setValorOperacao(ext1.getValorOperacao().subtract(transf.getValor()));

		Extrato ext2 = new Extrato();
		ext2.setConta(contadestino);
		ext2.setDataHoraMovimento(null);
		ext2.setOperacaoTipo(OperacaoTipo.TRANSFERENCIAREC.getDescricao());
		ext2.setValorOperacao(transf.getValor());

		extratoService.salvarExtrato(ext1);
		extratoService.salvarExtrato(ext2);

	}

	
	//metodo para buscar conta por id
	public ContaCorrente buscarConta(String num) {

		Long id = Long.parseLong(num);
		ContaCorrente conta = contaRepository.findById(id).orElseThrow();

		return conta;
	}
	
	
	// metodo para atualizar saldo
	public void attSaldo(ContaCorrente conta, BigDecimal valor) {
		conta.setSaldo(valor);
	}
	
	
	//metodo para listar todos os extratos
	public List<Extrato> extratosConta(String id) {
		Long idCc = Long.parseLong(id);
		List<Extrato> extratos = contaRepository.getExtratosConta(idCc);
		return extratos;
	}
	
	
	
	//metodo para fazer uma operação, usar 1 para SAQUE e 2 para Deposito
	public OperacaoRealizadaDTO operacao(OperacaoDTO dto) throws Exception {
		ContaCorrente cc = contaRepository.findById(dto.getContaId()).orElseThrow();
		Extrato ext = new Extrato();

		ext.setOperacaoTipo(OperacaoTipo.toEnum(dto.getOperacao()).getDescricao());
		ext.setValorOperacao(dto.getValor());

		ext.setConta(cc);
		
		switch (dto.getOperacao()) {
		case 1:
			if (dto.getValor().compareTo(cc.getSaldo()) == 1) {
				throw new Exception("ERRO! Valor do saque ultrapassa o saldo da conta");
			}
			cc.setSaldo(cc.getSaldo().subtract(dto.getValor()));

			break;
		case 2:
			cc.setSaldo(cc.getSaldo().add(dto.getValor()));
			break;
		default:
			throw new Exception("OPERAÇÃO INVÁLIDA!  Utilize 1 para Saque ou 2 para Depósito");

		}

		extratoService.salvarExtrato(ext);

		String mssg = OperacaoTipo.toEnum(dto.getOperacao()).getDescricao() + " realizado com sucesso!";
		String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

		OperacaoRealizadaDTO msgm = new OperacaoRealizadaDTO(HttpStatus.OK.value(), mssg, dataHora, dto.getValor(),
				cc.getSaldo());
		return msgm;
	}
}

package com.academico.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academico.model.Extrato;
import com.academico.repository.ExtratoRepository;

@Service
public class ExtratoService {

	@Autowired
	ExtratoRepository extratoRepository;
 // metodo para salvar extrato
	public void salvarExtrato(Extrato extrato) throws Exception {

		if (extrato.getDataHoraMovimento() == null) {
			String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			extrato.setDataHoraMovimento(today);
		}

		extratoRepository.save(extrato);
	}
	
	
}
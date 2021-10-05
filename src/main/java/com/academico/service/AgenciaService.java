package com.academico.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.academico.DTO.DetalhesDTO;
import com.academico.model.Agencia;
import com.academico.repository.AgenciaRepository;

@Service
public class AgenciaService {
    
    @Autowired
    AgenciaRepository agenciaRepository;
    
    //metodo para cadastrar agencia
    public void salvarAgencia(Agencia agencia) {
		this.agenciaRepository.save(agencia);
	}
    
   //metodo para listar todas as agencias
 	public List<Agencia> buscarAgencias(){
 		return this.agenciaRepository.findAll();
 	}
    
 	//metodo para bsucar por id
    public Agencia buscaAgencia(String id) throws Exception {
    	Long idN = Long.parseLong(id);
    	
    	Agencia agc = agenciaRepository.findById(idN).orElse(null);
    	
    	if (agc == null) {
    		throw new Exception("Agência não encontrada no nosso banco de dados");
    	
    	}
    	
		return agc;
    }
    
    
    //metodo para atualizar agencia 
    public void atualizarAgencia(Agencia novaAgencia, Long id) throws Exception {
		Agencia agenciaBD = this.agenciaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
		novaAgencia.setIdAgencia(id);
	    BeanUtils.copyProperties(novaAgencia, agenciaBD, "id");
	    this.salvarAgencia(agenciaBD);
	}
    
    //metodo para deletar agencia 
    public DetalhesDTO delete(String id) throws Exception {
		Long idN = Long.parseLong(id);
		
		Agencia ag = agenciaRepository.findById(idN).orElse(null);
		
		if (ag == null) {
			throw new Exception("Agência não encontrada!");
		}
		
		String msg = "Agencia " + ag.getNomeAgencia() + " deletada com sucesso!";
		String dat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	    DetalhesDTO dto = new DetalhesDTO(HttpStatus.OK.value(), msg, dat);
	    
	    agenciaRepository.deleteById(idN);
	    
	    return dto;
	}
}
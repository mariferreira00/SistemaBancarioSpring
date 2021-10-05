package com.academico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("SELECT c from CLIENTE c where c.cpf=?1")
	public Cliente findByCPF(String cpf);
}
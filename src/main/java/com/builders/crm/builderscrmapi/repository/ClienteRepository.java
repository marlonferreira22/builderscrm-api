package com.builders.crm.builderscrmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.builders.crm.builderscrmapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByNome(String nomeCliente);

}

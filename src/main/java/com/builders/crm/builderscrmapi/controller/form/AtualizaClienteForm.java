package com.builders.crm.builderscrmapi.controller.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.builders.crm.builderscrmapi.model.Cliente;
import com.builders.crm.builderscrmapi.repository.ClienteRepository;

import lombok.Data;

@Data
public class AtualizaClienteForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String dataNascimento;

	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);
		cliente.setNome(this.nome);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataFormatada = LocalDate.parse(this.dataNascimento, formatter);
		cliente.setDataNascimento(dataFormatada);
		return cliente;
	}
	
	
}

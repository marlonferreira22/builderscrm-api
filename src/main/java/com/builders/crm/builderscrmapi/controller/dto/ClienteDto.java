package com.builders.crm.builderscrmapi.controller.dto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.builders.crm.builderscrmapi.model.Cliente;

import lombok.Data;

@Data
public class ClienteDto {

	private Long id;
	private String nome;
	private String dataNascimento;
	private int idade;
	
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.dataNascimento = convertLocalDateToString(cliente.getDataNascimento());
		this.idade = CalculaIdade(cliente.getDataNascimento());
	}

	public static Page<ClienteDto> converter(Page<Cliente> clientes) {		
		return clientes.map(ClienteDto::new);
	}

	private int CalculaIdade(LocalDate dataNascimento) {		
		
		LocalDate dataAtual = LocalDate.now();
		
		return Period.between(dataNascimento, dataAtual).getYears();
	}
	
	private String convertLocalDateToString(LocalDate data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = data.format(formatter);
		
		return formattedString;
	}
}

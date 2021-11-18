package com.builders.crm.builderscrmapi.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private LocalDate dataNascimento;
	
	public Cliente() {		
	}
	
	public Cliente(String nome, String dataNascimento) {
		this.nome = nome;
		this.dataNascimento = convertStringToLocalDate(dataNascimento);
	}
	
	private LocalDate convertStringToLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataFormatada = LocalDate.parse(data, formatter);
		return dataFormatada;
	}

}

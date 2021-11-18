package com.builders.crm.builderscrmapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.builders.crm.builderscrmapi.model.Cliente;

import lombok.Data;

@Data
public class ClienteForm {
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String dataNascimento;
	
	public Cliente converter() {
		return new Cliente(nome, dataNascimento);
	}
}

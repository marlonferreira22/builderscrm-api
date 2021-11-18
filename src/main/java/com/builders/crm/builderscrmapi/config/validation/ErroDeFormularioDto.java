package com.builders.crm.builderscrmapi.config.validation;

import lombok.Data;

@Data
public class ErroDeFormularioDto {
	
	private String campo;
	private String erro;
	
	public ErroDeFormularioDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}	

}

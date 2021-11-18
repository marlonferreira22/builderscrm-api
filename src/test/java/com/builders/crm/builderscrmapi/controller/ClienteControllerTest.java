package com.builders.crm.builderscrmapi.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaCadastrarUmCliente() throws Exception {
		URI uri = new URI("/clientes");
		String json = "{\"nome\":\"teste\", \"dataNascimento\":\"1999-07-05\"}\"";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
	}
	
	@Test
	public void naoDeveriaCadastrarUmClienteSemNome() throws Exception {
		URI uri = new URI("/clientes");
		String json = "{\"dataNascimento\":\"1999-07-05\"}\"";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	public void naoDeveriaCadastrarUmClienteSemDataNascimento() throws Exception {
		URI uri = new URI("/clientes");
		String json = "{\"nome\":\"teste\"}\"";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	public void deveriaAtualizarUmCliente() throws Exception {
		URI uri = new URI("/clientes/1");
		String json = "{\"nome\":\"teste\", \"dataNascimento\":\"1999-07-05\"}\"";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}

}

package com.builders.crm.builderscrmapi.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.builders.crm.builderscrmapi.model.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository repository;

	@Test
	public void deveriaCarregarUmClientePeloNome() {
		
		String nomeCliente = "Marlon";
		Cliente cliente = repository.findByNome(nomeCliente);
		Assert.assertNotNull(cliente);
		Assert.assertEquals(nomeCliente, cliente.getNome());
	}
	
	@Test
	public void naoDeveriaCarregarUmClientePeloNome() {
		
		String nomeCliente = "Builders";
		Cliente cliente = repository.findByNome(nomeCliente);
		Assert.assertNull(cliente);
	}
	
	@Test
	public void deveriaCarregarUmClientePeloId() {
		
		Long id = 1L;
		Cliente cliente = repository.findById(id).get();
		Assert.assertNotNull(cliente);
		Assert.assertEquals(id, cliente.getId());
	}

}

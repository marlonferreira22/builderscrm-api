package com.builders.crm.builderscrmapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.builders.crm.builderscrmapi.controller.dto.ClienteDto;
import com.builders.crm.builderscrmapi.controller.form.AtualizaClienteForm;
import com.builders.crm.builderscrmapi.controller.form.ClienteForm;
import com.builders.crm.builderscrmapi.model.Cliente;
import com.builders.crm.builderscrmapi.repository.ClienteCustomRepository;
import com.builders.crm.builderscrmapi.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteCustomRepository clienteCustomRepository;

	@GetMapping
	@Cacheable(value="listaDeClientes")
	public Page<ClienteDto> listar(Pageable pageable){
		
		return ClienteDto.converter(clienteRepository.findAll(pageable));		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CacheEvict(value="listaDeClientes", allEntries = true)
	public ResponseEntity<ClienteDto> adicionar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		
		Cliente cliente = form.converter();	
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("/clientes/filtro?id={id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaDeClientes", allEntries = true)
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody AtualizaClienteForm form) {
		
		Optional<Cliente> optional = clienteRepository.findById(id);
		
		if(optional.isPresent()) {			
			Cliente cliente = form.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/filtro")
	public Page<Cliente> filtro(@RequestParam(required = false) Long id,
								@RequestParam(required = false) String nome,
								@RequestParam(required = false) String dataNascimento,
								Pageable pageable){
		
		List<Cliente> clientes = clienteCustomRepository.find(id, nome, dataNascimento);
		Page<Cliente> clientesPage = new PageImpl<Cliente>(clientes,
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()),
				clientes.size());
		
		return clientesPage;		
	}
}

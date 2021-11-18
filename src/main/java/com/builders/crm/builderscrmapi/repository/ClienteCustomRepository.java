package com.builders.crm.builderscrmapi.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.builders.crm.builderscrmapi.model.Cliente;

@Repository
public class ClienteCustomRepository {
	
	private final EntityManager em;
	
	public ClienteCustomRepository(EntityManager em) {
		this.em = em;
	}
	
	public List<Cliente> find(Long id, String nome, String dataNascimento){
		
		String query = "select C from Cliente as C where 1=1";
		
		if(id != null) {
			query += " and C.id = :id";
		}
		
		if (nome != null) {
			query += " and C.nome = :nome";
		}
		
		if (dataNascimento != null) {
			query += " and C.dataNascimento = :dataNascimento";
		}
		
		var q = em.createQuery(query, Cliente.class);
		
		if(id != null) {
			q.setParameter("id", id);
		}
		
		if (nome != null) {
			q.setParameter("nome", nome);
		}
		
		if (dataNascimento != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dataFormatada = LocalDate.parse(dataNascimento, formatter);
			q.setParameter("dataNascimento", dataFormatada);
		}
		
		return q.getResultList();
		
	}

}

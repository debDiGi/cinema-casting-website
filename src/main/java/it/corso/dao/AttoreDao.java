package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Attore;

public interface AttoreDao extends CrudRepository<Attore, Integer>{

	Attore findByEmailAndPassword(String email, String password);
	Attore findByEmail(String email);
}

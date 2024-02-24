package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Candidatura;

public interface CandidaturaDao extends CrudRepository<Candidatura, Integer> {

}

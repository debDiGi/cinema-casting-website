package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Film;
import it.corso.model.Attore;
import it.corso.model.Candidatura;

public interface CandidaturaDao extends CrudRepository<Candidatura, Integer> {
	
	List<Candidatura> findByAttore(Attore attore);
	Candidatura findByAttoreAndFilmAndStato(Attore attore, Film film, String stato);	
	List<Candidatura> findByAttoreAndFilm(Attore attore, Film film);
}

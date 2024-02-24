package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Film;

public interface FilmDao extends CrudRepository<Film, Integer>{

	List<Film> findByOpenTrue();
	List<Film> findByTitoloContainingIgnoreCase(String titolo);
}

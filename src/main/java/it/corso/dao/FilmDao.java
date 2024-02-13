package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Film;

public interface FilmDao extends CrudRepository<Film, Integer>{

}

package it.corso.service;

import java.util.List;
import java.util.Map;

import it.corso.model.Attore;
import it.corso.model.Film;

public interface FilmService {
	
	List<Film> getFilms();
	Map<String, List<Film>> getFilmsByGenere(List<Film> films);
	Film getFilmById(int id);
	List<Attore> getCast(int id);
	
}

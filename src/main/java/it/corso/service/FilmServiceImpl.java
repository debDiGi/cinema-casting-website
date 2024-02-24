package it.corso.service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.FilmDao;
import it.corso.model.Attore;
import it.corso.model.Film;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmDao filmDao;
	
	@Override
	public List<Film> getFilms() {	
		return (List<Film>) filmDao.findAll();
	}

	
	@Override
	public Film getFilmById(int id) {		
		return filmDao.findById(id).get();
	}

	@Override
	public Map<String, List<Film>> getFilmsByGenere(List<Film> films) {
        return films.stream()
                .collect(Collectors.groupingBy(Film::getGenere));	
	}

	@Override
	public List<Attore> getCast(int id) {
		Film film = filmDao.findById(id).orElse(null);	
		return film.getAttori();
	}


	@Override
	public List<Film> getOpenFilms() {		
		return filmDao.findByOpenTrue();
	}


	@Override
	public List<Film> getFilmsByTitolo(String titolo) {
		return filmDao.findByTitoloContainingIgnoreCase(titolo);
	}
}

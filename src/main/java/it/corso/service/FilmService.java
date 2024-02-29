package it.corso.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import it.corso.model.Attore;
import it.corso.model.Film;


public interface FilmService {
	
	List<Film> getFilms();
	List<Film> getOpenFilms();
	List<Film> getFilmsByTitolo(String titolo);
	Map<String, List<Film>> getFilmsByGenere(List<Film> films);
	Film getFilmById(int id);
	List<Attore> getCast(int id);
	void eliminaFilm(int id);
	void registraFilm(MultipartFile locandina, String trailer, String trama, LocalDate dataUscita, String genere, String titolo, boolean open);
	void modificaFilm(int id, String trailer, String trama, LocalDate dataUscita, String genere, String titolo, boolean open);
	void newLocandina(int id, MultipartFile locandina);
}

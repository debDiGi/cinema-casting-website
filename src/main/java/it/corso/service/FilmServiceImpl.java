package it.corso.service;


import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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


	@Override
	public void eliminaFilm(int id) {
		filmDao.delete(filmDao.findById(id).get());
	}


	@Override
	public void registraFilm(MultipartFile locandina, String trailer, String trama, LocalDate dataUscita, String genere,
			String titolo, boolean open) {
		Film film = new Film();
		
		if(locandina!=null && !locandina.isEmpty()) {
			try {
				// per trascendere dall estensione dell img
				String estensione = locandina.getContentType(); // questo mi da image/png o jpg che sia
				
				film.setLocandina("data:"+ estensione + ";base64," + Base64.getEncoder().encodeToString(locandina.getBytes()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		film.setTrailer(trailer);
		film.setTrama(trama);
		film.setDataUscita(dataUscita);
		film.setGenere(genere);
		film.setTitolo(titolo);
		film.setOpen(open);
		
		filmDao.save(film);
	}


	@Override
	public void modificaFilm(int id, String trailer, String trama, LocalDate dataUscita, String genere,
			String titolo, boolean open) {
			Film film = filmDao.findById(id).get();
				
		film.setTrailer(trailer);
		film.setTrama(trama);
		film.setDataUscita(dataUscita);
		film.setGenere(genere);
		film.setTitolo(titolo);
		film.setOpen(open);

		filmDao.save(film);
		
	}
	
	public void newLocandina(int id, MultipartFile locandina) {
		Film film = filmDao.findById(id).get();
		if(locandina!=null && !locandina.isEmpty()) {
			try {
				// per trascendere dall estensione dell img
				String estensione = locandina.getContentType(); // questo mi da image/png o jpg che sia
				
				film.setLocandina("data:"+ estensione + ";base64," + Base64.getEncoder().encodeToString(locandina.getBytes()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else { film.setLocandina(film.getLocandina());}
		filmDao.save(film);
	}

}

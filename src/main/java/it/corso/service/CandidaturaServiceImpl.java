package it.corso.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.CandidaturaDao;
import it.corso.model.Attore;
import it.corso.model.Candidatura;
import it.corso.model.Film;

@Service
public class CandidaturaServiceImpl implements CandidaturaService {

	@Autowired
	private CandidaturaDao candidaturaDao;
	
	@Override
	public void creaCandidatura(Attore attore, Film film) {
		Candidatura candidatura = new Candidatura();
		candidatura.setStato("Inviata");
		candidatura.setDataInvio(LocalDate.now());
		candidatura.setAttore(attore);
		candidatura.setFilm(film);
		
		if (candidaturaDao.findByAttoreAndFilmAndStato(attore, film, "Inviata")==null &&
				candidaturaDao.findByAttoreAndFilmAndStato(attore, film, "accettata")==null &&
				candidaturaDao.findByAttoreAndFilmAndStato(attore, film, "rifiutata")==null) {
			candidaturaDao.save(candidatura);
		}
			
	}

	//elimina o cambia stato?
	@Override
	public void eliminaCandidatura(int id) {
		Candidatura candidatura = candidaturaDao.findById(id).get();
		candidatura.setStato("Eliminata");
		candidatura.setDataEsito(LocalDate.now());
		candidaturaDao.save(candidatura);
	}

	@Override
	public List<Candidatura> getCandidatureAttore(Attore attore) {	
		List<Candidatura> candidature = candidaturaDao.findByAttore(attore);
		List<Candidatura> candidatureOrdinate = candidature.stream()
		        .sorted(Comparator.comparingLong(Candidatura::getId).reversed())
		        .collect(Collectors.toList());
		return candidatureOrdinate;
	}
	
	//per admin
	@Override
	public List<Candidatura> getCandidature() {	
		List<Candidatura> candidature = (List<Candidatura>) candidaturaDao.findAll();
		List<Candidatura> candidatureOrdinate = candidature.stream()
		        .sorted(Comparator.comparingLong(Candidatura::getId).reversed())
		        .collect(Collectors.toList());
		return candidatureOrdinate;
	}

	@Override
	public void respingiCandidatura(int id) {
		Candidatura candidatura = candidaturaDao.findById(id).get();
		candidatura.setStato("Respinta");
		candidatura.setDataEsito(LocalDate.now());
		candidaturaDao.save(candidatura);
		
	}

	@Override
	public void accettaCandidatura(int id) {
		Candidatura candidatura = candidaturaDao.findById(id).get();
		candidatura.setStato("Accettata");
		candidatura.setDataEsito(LocalDate.now());
		candidaturaDao.save(candidatura);
		
	}

	
	@Override
	public boolean getCandidaturaByAttoreAndFilm(Attore attore, Film film) {
	    List<Candidatura> candidature = candidaturaDao.findByAttoreAndFilm(attore, film);

	    if (candidature != null && !candidature.isEmpty()) {
	        return candidature.stream()
	                .anyMatch(candidatura -> !"eliminata".equalsIgnoreCase(candidatura.getStato()));
	    }

	    return false;
	}

}

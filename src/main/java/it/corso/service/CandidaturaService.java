package it.corso.service;

import java.util.List;

import it.corso.model.Attore;
import it.corso.model.Candidatura;
import it.corso.model.Film;

public interface CandidaturaService {

	public void creaCandidatura(Attore attore, Film film);
	public void eliminaCandidatura(int id);
	public void respingiCandidatura(int id);
	public void accettaCandidatura(int id);
	List<Candidatura> getCandidatureAttore(Attore attore);
	List<Candidatura> getCandidature();
	boolean getCandidaturaByAttoreAndFilm(Attore attore, Film film);
	
}

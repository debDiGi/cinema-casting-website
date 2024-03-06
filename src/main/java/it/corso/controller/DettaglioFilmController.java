package it.corso.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Admin;
import it.corso.model.Attore;
import it.corso.model.Film;
import it.corso.service.CandidaturaService;
import it.corso.service.FilmService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dettagliofilm")
public class DettaglioFilmController {

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CandidaturaService candidaturaService;
	
	@GetMapping
	public String getPage(
			@RequestParam ("id")int id,
			Model model, 
			HttpSession session) {
		Map<String, List<Film>> filmsMap = filmService.getFilmsByGenere(filmService.getFilms());
		Attore attore = (Attore) session.getAttribute("attore");
		boolean attoreLogged = attore!=null;
		Admin admin = (Admin) session.getAttribute("admin");
		boolean adminLogged = admin !=null;
		Film film = filmService.getFilmById(id);
		model.addAttribute("isCandidato", candidaturaService.getCandidaturaByAttoreAndFilm(attore, film));
		model.addAttribute("admin", admin);
		model.addAttribute("attore", attore);
		model.addAttribute("attoreLogged", attoreLogged);
		model.addAttribute("adminLogged", adminLogged);
		model.addAttribute("filmsMap", filmsMap);
		model.addAttribute("film",film);
		model.addAttribute("cast", filmService.getCast(id));
		
		return "dettagliofilm";
	}
	
	@GetMapping("/candidati")
	public String gestoreCandidatura(@RequestParam ("id")int id,
			HttpSession session,
			Model model) {
		
		Film film = filmService.getFilmById(id);
		Attore attore = (Attore) session.getAttribute("attore");
		
		
		if (attore!=null) {
			candidaturaService.creaCandidatura(attore, film);
			return "redirect:/dettagliofilm?id=" + id;
		}else {
			return "redirect:/registrazione";
		}
		
	}
	
	
}

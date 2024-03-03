package it.corso.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Admin;
import it.corso.model.Attore;
import it.corso.model.Film;
import it.corso.service.AttoreService;
import it.corso.service.FilmService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/dettaglioattore")
public class DettaglioAttoreController {

	@Autowired
	private AttoreService attoreService;
	@Autowired
	private FilmService filmService;
	
	@GetMapping
	public String getPage(
			@RequestParam ("id")int id,
			HttpSession session,
			Model model) {
		Map<String, List<Film>> filmsMap = filmService.getFilmsByGenere(filmService.getFilms());
		Attore attore = (Attore) session.getAttribute("attore");
		boolean attoreLogged = attore!=null;
		Admin admin = (Admin) session.getAttribute("admin");
		boolean adminLogged = admin !=null;
		model.addAttribute("admin", admin);
		model.addAttribute("attore", attore);
		model.addAttribute("attoreLogged", attoreLogged);
		model.addAttribute("adminLogged", adminLogged);
		model.addAttribute("filmsMap", filmsMap);
		model.addAttribute("attore", attoreService.getAttoreById(id));
		model.addAttribute("filmografia", attoreService.getFilmografia(id));
		return "dettaglioattore";
	}
	
}

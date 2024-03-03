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
import it.corso.service.FilmService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/genere")
public class GenereController {

	@Autowired
	private FilmService filmService;
	
	@GetMapping
	public String getPage(
			@RequestParam ("genere") String genere,
			Model model,
			HttpSession session
			) 
	{
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
		model.addAttribute("genere",genere);
		
		return "genere";
	}
	
}

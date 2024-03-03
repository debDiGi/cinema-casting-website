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



@Controller
@RequestMapping("/") //mappatura - EndPoint
public class IndexController {

	@Autowired // inietto un oggetto - dependencies injection
	//chiede a spring di fornire un ' istanza di FilmService e l'assegna alla proprietà filmService 
	private FilmService filmService;
	

	@GetMapping
	public String getPage(HttpSession session, Model model) {
		Map<String, List<Film>> filmsMap = filmService.getFilmsByGenere(filmService.getFilms());
		List<Film> openFilms = filmService.getOpenFilms();
		Admin admin = (Admin) session.getAttribute("admin");
		boolean adminLogged = admin !=null;
		model.addAttribute("admin", admin);
		Attore attore = (Attore) session.getAttribute("attore");
		boolean attoreLogged = attore!=null;
		model.addAttribute("filmsMap", filmsMap);
		model.addAttribute("openFilms",openFilms);
		model.addAttribute("adminLogged", adminLogged);
		model.addAttribute("attoreLogged", attoreLogged);
		model.addAttribute("attore", attore);
		
		
		return "index";
	}
	
}

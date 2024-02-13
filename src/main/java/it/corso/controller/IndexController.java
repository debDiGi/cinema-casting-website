package it.corso.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import it.corso.model.Film;
import it.corso.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/") //mappatura - EndPoint
public class IndexController {

	@Autowired // inietto un oggetto - dependencies injection
	//chiede a spring di fornire un ' istanza di FilmService e l'assegna alla proprietà filmService 
	private FilmService filmService;
	

	@GetMapping
	public String getPage(Model model) {
		Map<String, List<Film>> filmsMap = filmService.getFilmsByGenere(filmService.getFilms());
		model.addAttribute("filmsMap", filmsMap);
		return "index";
	}
	
}

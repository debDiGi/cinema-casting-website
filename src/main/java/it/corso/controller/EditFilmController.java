package it.corso.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import it.corso.model.Film;
import it.corso.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/editfilm")
public class EditFilmController {
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping
	public String getPage(
		        @RequestParam(name = "id") int id,
		        @RequestParam (name="titoloErr", required=false) String titoloErr,
				@RequestParam (name="incompleto", required=false) String incompleto,
				Model model
		) {
		    // Recupera il film da modificare dal database
		    Film filmEdit = filmService.getFilmById(id);

		    // Passa il film al model
		    model.addAttribute("filmEdit", filmEdit);
		    Map<String, List<Film>> filmsMap = filmService.getFilmsByGenere(filmService.getFilms());
			model.addAttribute("filmsMap", filmsMap);
			model.addAttribute("incompleto",incompleto!=null);
			model.addAttribute("titoloErr", titoloErr!=null);
					
			return "editfilm";
		}
	
		
		@PostMapping
		public String editManager(
				@RequestParam (name="id", required=false) int id,
				@RequestParam(name="locandina",required = false) MultipartFile locandina,
				@RequestParam ("trailer") String trailer,
				@RequestParam("trama") String trama,
				@RequestParam(name="dataUscita", required=false) LocalDate dataUscita,
				@RequestParam("genere") String genere,
				@RequestParam("titolo") String titolo,
				@RequestParam("open") boolean open,
			    Model model
				) {
			
			if(!locandina.isEmpty()) {
				filmService.newLocandina(id, locandina);
			}
			
			if(dataUscita==null) {
				dataUscita= filmService.getFilmById(id).getDataUscita();
			}
		    try {
		        filmService.modificaFilm(id, trailer, trama, dataUscita, genere, titolo, open);
		        return "redirect:/adminpage"; //add success
		    } catch (Exception e) {
		        // Gestisci l'errore, ad esempio, aggiungi un attributo al model per visualizzare un messaggio di errore.
		        model.addAttribute("errore", "Si è verificato un errore durante il salvataggio del film.");
		        return "redirect:/editfilm?err";
		    }
		}
		
	}
	



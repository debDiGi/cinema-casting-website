package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.service.AttoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/dettaglioattore")
public class DettaglioAttoreController {

	@Autowired
	private AttoreService attoreService;
	
	@GetMapping
	public String getPage(
			@RequestParam ("id")int id,
			Model model) {
		model.addAttribute("attore", attoreService.getAttoreById(id));
		model.addAttribute("filmografia", attoreService.getFilmografia(id));
		return "dettaglioattore";
	}
	
}

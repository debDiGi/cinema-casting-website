package it.corso.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Attore;
import it.corso.service.AttoreService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/formattore")
public class FormAttoreController {
	
	@Autowired
	private AttoreService attoreService;
	
	@GetMapping
	public String getPage(
			@RequestParam (name="etaerr", required = false)String etaErr,
			Model model,
			HttpSession session
			) 
	{
	
	Attore attore = (Attore) session.getAttribute("attore");
	
	model.addAttribute("attore", attore);
	model.addAttribute("etaErr", etaErr!=null);
	 return "formattore";
}
	
	@PostMapping
	public String formManager(
			@RequestParam (name="nome",required = false) String nome,
			@RequestParam(name="cognome",required = false) String cognome,
			@RequestParam(name="dataNascita",required = false) LocalDate dataNascita,
			@RequestParam(name="email",required = false) String email,
			@RequestParam(name="password",required = false) String password,
		    HttpSession session
		    ) {
		
		
		//msg data err(min/max)
		LocalDate etaMin = LocalDate.parse("2005-02-07");
		LocalDate etaMax = LocalDate.parse("1935-02-07");		
		if (dataNascita.isAfter(etaMin)||dataNascita.isBefore(etaMax)) 
		{
			return "redirect:/formattore?etaerr";
		}
		
		Attore attore = (Attore) session.getAttribute("attore");
		attoreService.modificaAttore(attore.getId(), nome, cognome, dataNascita, password, email);
	    return "redirect:/riservata";
		
	}
	
}

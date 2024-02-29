package it.corso.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import it.corso.model.Attore;
import it.corso.service.AttoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/registrazione")
public class FormController {
	
	@Autowired
	private AttoreService attoreService;
	
	@GetMapping
	public String getPage(
			@RequestParam (name="err", required=false) String emailErr,
			@RequestParam (name="etaerr", required = false)String etaErr,
			@RequestParam (name="incompleto", required=false) String incompleto,
			Model model
			) 
	{
		Attore attore = new Attore();
		model.addAttribute("attore", attore);
		model.addAttribute("emailErr", emailErr!=null);
		model.addAttribute("etaErr", etaErr!=null);
		model.addAttribute("incompleto",incompleto!=null);
		return "form";
	}
	
	@PostMapping
	public String formManager(
			@RequestParam ("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("dataNascita") LocalDate dataNascita,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
		    @RequestParam(name="ritratto",required = false) MultipartFile ritratto,
		    @RequestParam(name="foto",required = false) MultipartFile foto
		    ) {
		
		
		if (attoreService.checkAttore(email)!=null) 
		{			
			return "redirect:/registrazione?err"; // aggiungo '?err' per il redirect in caso di email esistente, così nel getPage non è null e si attiva th:if
		}
		
		//msg dati mancanti err
		if (nome.isBlank() || cognome.isBlank() || email.isBlank() || password.isBlank()) {
			return "redirect:/registrazione?incompleto";
		}

		//msg data err(min/max)
		LocalDate etaMin = LocalDate.parse("2005-02-07");
		LocalDate etaMax = LocalDate.parse("1935-02-07");		
		if (dataNascita.isAfter(etaMin)||dataNascita.isBefore(etaMax)) 
		{
			return "redirect:/registrazione?etaerr";
		}

		attoreService.registraAttore(nome, cognome, dataNascita, password, email, ritratto, foto);
	    return "redirect:/login";
		
	}
	
	
}

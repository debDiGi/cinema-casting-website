package it.corso.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Attore;
import it.corso.service.AttoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/riservata")
public class RiservataController 
{
	@Autowired
	private AttoreService attoreService;
	
	@GetMapping
	public String getPage(
			Model model,
			HttpSession session
			) 
	{
		Attore attore = (Attore) session.getAttribute("attore");
		model.addAttribute("attore", attore);
		return "riservata";
	}
	
	@PostMapping
	public String gestioneModifica(@RequestParam(name="ritratto",required = false) MultipartFile ritratto,
		    @RequestParam(name="foto",required = false) MultipartFile foto, HttpSession session, Model model) {
		Attore attore = (Attore) session.getAttribute("attore");
		
		attoreService.newRitratto(attore.getId(), ritratto,session);
		model.addAttribute("attore", attore);
		return "redirect:/riservata";
	}
	
	@GetMapping("/logout")
	public String gestioneLogout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/cancellaaccount")
	public String cancellaAttore(HttpSession session) 
		{
		Attore attore = (Attore) session.getAttribute("attore");
			attoreService.cancellaAccount(attoreService.getAttoreById(attore.getId()));
			session.invalidate();
			return "redirect:/";
	}
	
	
	
}

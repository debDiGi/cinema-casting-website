package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.corso.model.Admin;
import it.corso.model.Attore;
import it.corso.model.Candidatura;
import it.corso.model.Film;
import it.corso.service.AdminService;
import it.corso.service.AttoreService;
import it.corso.service.CandidaturaService;
import it.corso.service.FilmService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/adminpage")
public class AdminPageController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CandidaturaService candidaturaService;
	@Autowired
	private AttoreService attoreService;
	@Autowired
	private FilmService filmService;
	
	@GetMapping
	public String getPage(
			@RequestParam (name="err", required=false) String nomeErr,
			@RequestParam (name="incompleto", required=false) String incompleto,
			@RequestParam (name="success", required = false) String success,
			Model model,
			HttpSession session
			) 
	{
		
		List<Attore> attori = attoreService.getAttori();
		List<Film> films = filmService.getFilms();
		List<Admin> utenze = adminService.getAdmins();
		List<Candidatura> candidature = candidaturaService.getCandidature();
		Admin admin = (Admin) session.getAttribute("admin");
		boolean adminLogged = admin.getNome().equals("boss");
		model.addAttribute("attori", attori);
		model.addAttribute("films", films);
		model.addAttribute("adminLogged", adminLogged);
		model.addAttribute("candidature", candidature);
		model.addAttribute("utenze", utenze);
		model.addAttribute("admin", admin);
		model.addAttribute("incompleto",incompleto!=null);
		model.addAttribute("nomeErr", nomeErr!=null);
		model.addAttribute("success",success!=null);
		return "adminpage";
	}
	
	@GetMapping("/logout")
	public String gestioneLogout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/canccandidatura")
	public String cancellaCandidatura(@RequestParam ("id") int id) 
		{
		candidaturaService.eliminaCandidatura(id);
			return "redirect:/adminpage";
	}
	
	@GetMapping("/accetta")
	public String accettaCandidatura(@RequestParam ("id") int id) 
		{
		candidaturaService.accettaCandidatura(id);
			return "redirect:/adminpage";
	}
	
	@GetMapping("/respingi")
	public String respingiCandidatura(@RequestParam ("id") int id) 
		{
		candidaturaService.respingiCandidatura(id);
			return "redirect:/adminpage";
	}
	
	@PostMapping
	public String formManager(
			@RequestParam ("nome") String nome,
			@RequestParam ("psw") String psw
			)
	{	
		
		if (adminService.checkAdmin(nome)!=null) 
		{			
			return "redirect:/adminpage?err"; // aggiungo '?err' per il redirect in caso di email esistente, così nel getPage non è null e si attiva th:if
		}
		
		//msg dati mancanti err
		if (nome.isBlank() || psw.isBlank()) {
			return "redirect:/adminpage?incompleto";
		}
		
		adminService.creaAdmin(nome, psw, 2);
		return "redirect:/adminpage?success";
		
	}
	
	@GetMapping("/cancellaAdmin")
	public String cancellaAdmin(@RequestParam ("id") int id) 
		{
			adminService.eliminaAdminById(id);
			return "redirect:/adminpage";
	}
	
	//ATTORI
	@GetMapping("/cancellaattore")
	public String cancellaAttore(@RequestParam ("id") int id) 
		{
			attoreService.cancellaAccount(attoreService.getAttoreById(id));
			return "redirect:/adminpage";
	}
	
	//FILM
		@GetMapping("/cancellafilm")
		public String cancellaFilm(@RequestParam ("id") int id) 
			{
				filmService.eliminaFilm(id);
				return "redirect:/adminpage";
		}
	
		
}

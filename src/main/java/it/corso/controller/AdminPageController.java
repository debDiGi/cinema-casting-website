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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/adminpage")
public class AdminPageController {

	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);

	
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
		Admin newAdmin = new Admin();
		boolean adminLogged = admin.getNome().equals("boss");
		model.addAttribute("newAdmin", newAdmin);
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
		// Log della richiesta POST ricevuta
        logger.info("Richiesta POST ricevuta su /adminpage");
        
		if (adminService.checkAdmin(nome)!=null) 
		{			
			// Log di errore se il nomeUtente esiste già
            logger.error("Tentativo di creare un admin con un'email già esistente: {}", nome);
			return "redirect:/adminpage?err"; // aggiungo '?err' per il redirect in caso di email esistente, così nel getPage non è null e si attiva th:if
		}
		
		//msg dati mancanti err
		if (nome.isBlank() || psw.isBlank()) {
			logger.debug("Dati incompleti durante la creazione di un nuovo admin");
			return "redirect:/adminpage?incompleto";
		}
		
		// Log dell'azione di creazione di un nuovo admin
        logger.info("Creazione di un nuovo admin: {}", nome);
		
		adminService.creaAdmin(nome, psw, 2);
		
		// Log dell'avvenuta creazione dell'admin
        logger.info("Nuovo admin creato con successo: {}", nome);
        
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

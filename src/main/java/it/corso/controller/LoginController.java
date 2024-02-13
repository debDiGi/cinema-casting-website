package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.corso.service.AttoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AttoreService attoreService;
	
	@GetMapping
	public String getPage(
			@RequestParam (name="err", required=false) String logError,
			HttpSession session,
			Model model 
			) 
	{
		
		if (session.getAttribute("attore")!=null) 
			return"redirect:/riservata";
		model.addAttribute("logError", logError!=null);
		return "login";
	}
	
	@PostMapping
	public String gestioneLogin(@RequestParam ("email") String email,
							  @RequestParam("password") String password,
							  HttpSession session,
							  Model model) {
		
		if (!attoreService.controlloLogin(email, password, session)) 
			return "redirect:/login?err";
		return "redirect:/riservata";
	}
	
	
}

//CHIEDI A DAVIDE
//Create a XML file tomcat-users.xml for login functionality.

package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/loginadmin")
public class LoginAdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public String getPage(
			@RequestParam (name="err", required=false) String logError,
			HttpSession session,
			Model model 
			) 
	{
		
		if (session.getAttribute("admin")!=null) 
			return"redirect:/adminpage";
		model.addAttribute("logError", logError!=null);
		return "loginadmin";
	}
	
	@PostMapping
	public String gestioneLogin(@RequestParam ("nome") String nome,
							  @RequestParam("psw") String psw,
							  HttpSession session,
							  Model model) {
		
		if (!adminService.controlloLogin(nome, psw, session)) 
			return "redirect:/loginadmin?err";
		return "redirect:/adminpage";
	}
		
}

//CHIEDI A CHAT
//Create a XML file tomcat-users.xml for login functionality.

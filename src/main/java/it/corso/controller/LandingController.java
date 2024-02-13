package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/landing")
public class LandingController {

	@GetMapping
	public String getPage() {
		return "landing";
	}
	
}

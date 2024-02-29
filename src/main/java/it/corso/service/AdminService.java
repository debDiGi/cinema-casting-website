package it.corso.service;

import java.util.List;

import it.corso.model.Admin;

import jakarta.servlet.http.HttpSession;

public interface AdminService {

	void creaAdmin(String nome, String psw, int livello);
	List<Admin> getAdmins();
	public void eliminaAdminById(int id);
	Admin checkAdmin(String nome);
	public boolean controlloLogin(String nome, String psw, HttpSession session);
	
	
}

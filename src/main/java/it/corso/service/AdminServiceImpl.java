package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.AdminDao;
import it.corso.model.Admin;
import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public void creaAdmin(String nome, String psw, int livello) {
		Admin admin = new Admin();
		admin.setNome(nome);
		admin.setPsw(psw);
		admin.setLivello(2);
		adminDao.save(admin);
	}

	@Override
	public List<Admin> getAdmins() {
		return (List<Admin>) adminDao.findAll();
	}

	@Override
	public void eliminaAdminById(int id) {
		adminDao.deleteById(id);
	}

	@Override
	public Admin checkAdmin(String nome) {
		return adminDao.findByNome(nome);
	}

	@Override
	public boolean controlloLogin(String nome, String psw, HttpSession session) {
		Admin admin = adminDao.findByNomeAndPsw(nome,psw);			
		if (admin!=null) {
			if (nome.equalsIgnoreCase(admin.getNome()) && psw.equals(admin.getPsw())) {
				admin.setNome(nome);
				admin.setPsw(psw);
				session.setAttribute("admin", admin);
				return true;
			}
		}
		 
		return false;
	}


}

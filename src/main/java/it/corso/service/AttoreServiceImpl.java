package it.corso.service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.corso.dao.AttoreDao;
import it.corso.model.Attore;
import it.corso.model.Film;
import jakarta.servlet.http.HttpSession;


@Service
public class AttoreServiceImpl implements AttoreService {
	
	@Autowired
	private AttoreDao attoreDao;
	
	@Override
	public void cancellaAccount(Attore attore) {
		attoreDao.delete(attore);
	}
	
	@Override
	public List<Attore> getAttori() {
		return (List<Attore>) attoreDao.findAll();
	}

	@Override
	public Attore getAttoreById(int id) {
		
		return attoreDao.findById(id).get();
	}

	@Override
	public List<Film> getFilmografia(int id) {
		Attore attore = attoreDao.findById(id).orElse(null);
		return attore.getFilms();
	}

	@Override
	public void registraAttore(String nome, String cognome, LocalDate dataNascita, String password, String email,
			MultipartFile ritratto, MultipartFile foto) {
		Attore attore = new Attore();
		attore.setNome(nome);
		attore.setCognome(cognome);
		attore.setDataNascita(dataNascita);
		attore.setPassword(password);
		attore.setEmail(email);
		if(ritratto!=null && !ritratto.isEmpty()) {
			try {
				// per trascendere dall estensione dell img
				String estensione = ritratto.getContentType(); // questo mi da image/png o jpg che sia
				
				attore.setRitratto("data:"+ estensione + ";base64," + Base64.getEncoder().encodeToString(ritratto.getBytes()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		if(foto!=null && !foto.isEmpty()) {
			try {
				
				String estensione = foto.getContentType(); 
				
				attore.setFoto("data:"+ estensione + ";base64," + Base64.getEncoder().encodeToString(foto.getBytes()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		attoreDao.save(attore);
	
		
	}

	@Override
	public boolean controlloLogin(String email, String password, HttpSession session) {
		
		Attore attore = attoreDao.findByEmailAndPassword(email,password);			
		if (attore!=null) {
			if (email.equalsIgnoreCase(attore.getEmail()) && password.equals(attore.getPassword())) {
				attore.setEmail(email);
				attore.setPassword(password);
				session.setAttribute("attore", attore);
				return true;
			}
		}
		 
		return false;
	}

	@Override
	public Attore checkAttore(String email) {
			return attoreDao.findByEmail(email);
	}

	@Override
	public void newRitratto(int id, MultipartFile newRitr, HttpSession session) {
		Attore attore = attoreDao.findById(id).get();
			try {
				
				String estensione = newRitr.getContentType(); 
				
				attore.setRitratto("data:"+ estensione + ";base64," + Base64.getEncoder().encodeToString(newRitr.getBytes()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		session.setAttribute("attore", attore);
		attoreDao.save(attore);
	}

	@Override
	public void newFoto(int id, MultipartFile newFoto, HttpSession session) {
		Attore attore = attoreDao.findById(id).get();
		try {
			
			String estensione = newFoto.getContentType();
			
			attore.setRitratto("data:"+ estensione + ";base64," + Base64.getEncoder().encodeToString(newFoto.getBytes()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	session.setAttribute("attore", attore);
	attoreDao.save(attore);
		
	}

	@Override
	public List<Attore> getAttoriByNomeOCognome(String nomeCognome) {

		return attoreDao.findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(nomeCognome, nomeCognome);
	}

	
}

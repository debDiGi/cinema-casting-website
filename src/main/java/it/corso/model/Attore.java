package it.corso.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "attore")
public class Attore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="nome")
	private String nome;
	@Column(name="cognome")
	private String cognome;
	@Column(name="data_nascita")
	private LocalDate dataNascita;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="ritratto")
	private String ritratto;
	@Column(name="foto")
	private String foto;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(
			name = "film_attore",
			joinColumns = @JoinColumn(name = "id_attore", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id")
	)
	private List<Film> films = new ArrayList<>();
	
	public List<Film> getFilms() {
		return films;
	}
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRitratto() {
		return ritratto;
	}
	public void setRitratto(String ritratto) {
		this.ritratto = ritratto;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public List<Candidatura> getCandidature() {
		return candidature;
	}
	public void setCandidature(List<Candidatura> candidature) {
		this.candidature = candidature;
	}

	@OneToMany(mappedBy = "attore")
    private List<Candidatura> candidature;
	
}

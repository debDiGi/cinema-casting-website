package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name="candidatura")
public class Candidatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "stato", columnDefinition = "VARCHAR(25) DEFAULT 'inviata'")
	private String stato;
	
	@ManyToOne
    @JoinColumn(name = "id_attore")
    private Attore attore;

    @ManyToOne
    @JoinColumn(name = "id_film")
    private Film film;
	
	//@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Attore getAttore() {
		return attore;
	}
	public void setAttore(Attore attore) {
		this.attore = attore;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	
}

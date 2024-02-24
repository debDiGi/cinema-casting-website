package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="candidatura_film")
public class CandidaturaFilm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="id_candidatura")
	private int idCandidatura;
	@Column(name="id_film")
	private int idFilm;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCandidatura() {
		return idCandidatura;
	}
	public void setIdCandidatura(int idCandidatura) {
		this.idCandidatura = idCandidatura;
	}
	public int getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

}
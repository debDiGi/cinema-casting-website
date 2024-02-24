package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="candidatura_attore")
public class CandidaturaAttore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="id_candidatura")
	private int idCandidatura;
	@Column(name="id_attore")
	private int idAttore;
	
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
	public int getIdAttore() {
		return idAttore;
	}
	public void setIdAttore(int idAttore) {
		this.idAttore = idAttore;
	}
	
	
}

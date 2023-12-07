package org.java.spring.db.pojo;

import org.hibernate.validator.constraints.Length;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 20)
    @NotBlank(message = "Il nome non può essere vuoto")
    @Length(min = 3, max = 20, message = "Il nome della pizza deve essere compreso tra 3 e 20 caratteri")
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "La descrizione non può essere vuota")
    @Length(min = 5, max = 100, message = "La descrizione della pizza deve essere compresa tra 5 e 100 caratteri")
    private String descrizione;
    
    @NotBlank(message = "L url della foto non può essere vuoto")
    @URL(protocol = "https", message = "Devi inserire un URL valido (Protocollo HTTPS)")
    private String foto;
    
    @Positive(message = "Il prezzo deve essere maggiore di 0")
    private double prezzo;
	
	
	public Pizza() {}
	public Pizza(String nome, String descrizione, String foto, double prezzo) {
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);	
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getPrezzoFormattato() {
		return String.format("%.2f", getPrezzo()) + "€";
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getNome() + " - " 
				+ getDescrizione() + " (" + getPrezzo() + ")";
	}
	
}

package org.ada.restapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class
//Falta crear el ProductRepository interfaz
public class Producto {
	@Id
	private String ean; 
	private double kg;
	private String titulo;
	private double precioSugerido;
	
	@JoinColumn 
	@ManyToOne 
	private Categoria categoria;
	
	private String marca;
	private String compania;
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public double getKg() {
		return kg;
	}
	public void setKg(double kg) {
		this.kg = kg;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public double getPrecioSugerido() {
		return precioSugerido;
	}
	public void setPrecioSugerido(double precioSugerido) {
		this.precioSugerido = precioSugerido;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}

	
}
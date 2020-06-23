package org.ada.restapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class
public class Reporte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Date fecha; 
	
	@JoinColumn 
	@ManyToOne 
	private Link linkSku;
	
	private String titulo;

	private double precio;
	
	private double precio2;
	private String stock;

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Link getLinkSku() {
		return linkSku;
	}

	public void setLinkSku(Link linkSku) {
		this.linkSku = linkSku;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio2() {
		return precio2;
	}

	public void setPrecio2(double precio2) {
		this.precio2 = precio2;
	}
	
	
	
}
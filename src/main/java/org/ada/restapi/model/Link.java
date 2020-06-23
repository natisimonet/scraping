package org.ada.restapi.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class Link {
	@Id
	private Integer id;
	
	@JoinColumn 
	@ManyToOne 
	private Producto producto;
	
	@JoinColumn 
	@ManyToOne 
	private Etailer etailer;
	
	private String link;
	
	private String kg;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Etailer getEtailer() {
		return etailer;
	}

	public void setEtailer(Etailer etailer) {
		this.etailer = etailer;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	
	
}

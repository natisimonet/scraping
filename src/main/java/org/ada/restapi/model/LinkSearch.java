package org.ada.restapi.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LinkSearch {
	@Id
	private Integer id; 
	
	@JoinColumn 
	@ManyToOne 
	private Etailer etailer;
	private String tipo;
	
	private String categoryOrKW;
	@JoinColumn 
	@ManyToOne 
	private Categoria Categoria;
	private String link;

	private int totalProd;

	public LinkSearch () {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Etailer getEtailer() {
		return etailer;
	}

	public void setEtailer(Etailer etailer) {
		this.etailer = etailer;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoryOrKW() {
		return categoryOrKW;
	}

	public void setCategoryOrKW(String categoryOrKW) {
		this.categoryOrKW = categoryOrKW;
	}

	public Categoria getCategoria() {
		return Categoria;
	}

	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getTotalProd() {
		return totalProd;
	}

	public void setTotalProd(int totalProd) {
		this.totalProd = totalProd;
	}

	
}

package org.ada.restapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class
public class ReporteSearch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Date fecha;
	@JoinColumn 
	@ManyToOne 
	private LinkSearch linkSearch;
	private String loc01;
	private String loc02;
	private String loc03;
	private String loc04;
	private String loc05;
	private String loc06;
	private String loc07;
	private String loc08;
	private String loc09;
	private String loc10;
	private String marca01;
	private String marca02;
	private String marca03;
	private String marca04;
	private String marca05;
	private String marca06;
	private String marca07;
	private String marca08;
	private String marca09;
	private String marca10;
	
	
	
	public String getMarca01() {
		return marca01;
	}
	public void setMarca01(String marca01) {
		this.marca01 = marca01;
	}
	public String getMarca02() {
		return marca02;
	}
	public void setMarca02(String marca02) {
		this.marca02 = marca02;
	}
	public String getMarca03() {
		return marca03;
	}
	public void setMarca03(String marca03) {
		this.marca03 = marca03;
	}
	public String getMarca04() {
		return marca04;
	}
	public void setMarca04(String marca04) {
		this.marca04 = marca04;
	}
	public String getMarca05() {
		return marca05;
	}
	public void setMarca05(String marca05) {
		this.marca05 = marca05;
	}
	public String getMarca06() {
		return marca06;
	}
	public void setMarca06(String marca06) {
		this.marca06 = marca06;
	}
	public String getMarca07() {
		return marca07;
	}
	public void setMarca07(String marca07) {
		this.marca07 = marca07;
	}
	public String getMarca08() {
		return marca08;
	}
	public void setMarca08(String marca08) {
		this.marca08 = marca08;
	}
	public String getMarca09() {
		return marca09;
	}
	public void setMarca09(String marca09) {
		this.marca09 = marca09;
	}
	public String getMarca10() {
		return marca10;
	}
	public void setMarca10(String marca10) {
		this.marca10 = marca10;
	}
	public Integer getId() {
		return id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public LinkSearch getLinkSearch() {
		return linkSearch;
	}
	public void setLinkSearch(LinkSearch linkSearch) {
		this.linkSearch = linkSearch;
	}
	public String getLoc01() {
		return loc01;
	}
	public void setLoc01(String loc01) {
		this.loc01 = loc01;
	}
	public String getLoc02() {
		return loc02;
	}
	public void setLoc02(String loc02) {
		this.loc02 = loc02;
	}
	public String getLoc03() {
		return loc03;
	}
	public void setLoc03(String loc03) {
		this.loc03 = loc03;
	}
	public String getLoc04() {
		return loc04;
	}
	public void setLoc04(String loc04) {
		this.loc04 = loc04;
	}
	public String getLoc05() {
		return loc05;
	}
	public void setLoc05(String loc05) {
		this.loc05 = loc05;
	}
	public String getLoc06() {
		return loc06;
	}
	public void setLoc06(String loc06) {
		this.loc06 = loc06;
	}
	public String getLoc07() {
		return loc07;
	}
	public void setLoc07(String loc07) {
		this.loc07 = loc07;
	}
	public String getLoc08() {
		return loc08;
	}
	public void setLoc08(String loc08) {
		this.loc08 = loc08;
	}
	public String getLoc09() {
		return loc09;
	}
	public void setLoc09(String loc09) {
		this.loc09 = loc09;
	}
	public String getLoc10() {
		return loc10;
	}
	public void setLoc10(String loc10) {
		this.loc10 = loc10;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	
}

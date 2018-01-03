package it.lcianci.test.rest.pojo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Questa classe è un POJO per la gestione degli utenti in anagrafica
 * 
 * @author lcianci
 *
 */

/*
 * Questa annotazione di JAXB permette il binding automatico della mia classe in formato XML o JSON usando
 * il nome classe e i nomi di variabile per generare i tag dell'xml in output (o del json)
 */
@XmlRootElement
@XmlType(propOrder = { "place", "cap", "city" })
public class Address {
	
	private String place;
	private Integer cap;
	private String city;
	
	public Address() {}
	
	public Address(String place, Integer cap, String city) {
		this.place = place;
		this.cap   = cap;
		this.city  = city;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPlace() {
		return place;
	}
	public void setCap(Integer cap) {
		this.cap = cap;
	}
	public Integer getCap() {
		return cap;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}

}

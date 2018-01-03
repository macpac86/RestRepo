package it.lcianci.test.rest.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
@XmlType(propOrder = { "id", "surname", "name", "address", "otherAddress" })
public class User {
	
	private Integer id;
	private String name;
	private String surname;
	private Address address;
		
	private ArrayList<Address> otherAddress;
		
	public User() {}
	
	public User(Integer id, String nome, String surname) {
		this.id = id;
		this.name = nome;
		this.surname = surname;
		
		otherAddress = new ArrayList<Address>();
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSurname() {
		return surname;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}

	@XmlElementWrapper(name = "otherAddress")
	@XmlElement(name = "address")
	public void setOtherAddress(ArrayList<Address> otherAddress) {
		this.otherAddress = otherAddress;
	}

	public ArrayList<Address> getOtherAddress() {
		return otherAddress;
	}
	
}

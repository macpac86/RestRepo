package it.lcianci.test.rest.dao;

import it.lcianci.test.rest.pojo.Address;
import it.lcianci.test.rest.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Questa è una classe MOCK-DAO realizzata come oggetto enumeration.
 * instance è l'unico elemento dell'enumerazione e a questo viene applicato il costruttore.
 * A questo punto posso invocare i metodi della enumerazione sul singolo elemento scrivendo:
 * UserDAO.instance.getModel()
 * 
 * @author lcianci
 *
 */
public enum UserDAO {
	myDatabase;
	
	private Map<String, User> mySchema = new HashMap<String, User>();
	
	private UserDAO() {
		
		ArrayList<Address> al = new ArrayList<Address>();
		al.add(new Address("Via ABC 123", 20100, "Milano2"));
		al.add(new Address("Via ABC 123", 20100, "Milano3"));
		
		User user = new User(1, "Luca", "Cianci");
		user.setAddress(new Address("Via ABC 123", 20100, "Milano"));
		user.setOtherAddress(al);
		mySchema.put("1", user);
		
		user = new User(2, "Luca", "Cianci");
		user.setAddress(new Address("Via DEF 456", 20100, "Siracusa"));
		user.setOtherAddress(al);
		mySchema.put("2", user);
		
	}
	public Map<String, User> getModel(){
		return mySchema;
	}
	
}

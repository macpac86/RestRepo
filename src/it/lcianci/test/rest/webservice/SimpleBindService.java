package it.lcianci.test.rest.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.lcianci.test.rest.dao.UserDAO;
import it.lcianci.test.rest.pojo.User;

/**
 * Questo semplice WebService permette di testare il binding in formato XML o JSON
 * di JAXB ottenuto semplicemente annotando i POJO che vengono restituiti.
 * 
 * @author lcianci
 *
 */

@Path("/simpleBind")
public class SimpleBindService {

	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getXML() {
		return UserDAO.myDatabase.getModel().get("2");
//		User user = new User();
//		user.setNome("Luca");
//		user.setCognome("Cianci");
//		user.setAddress(new Address("Via Cottolengo 5", 20143, "Milano"));
//		
//		return user;
	}
	
	@GET
	@Produces( { MediaType.TEXT_XML })
	public User getHTML() {
		return UserDAO.myDatabase.getModel().get("1");
//		User user = new User();
//		user.setNome("Luca");
//		user.setCognome("Cianci");
//		user.setAddress(new Address("Via Cottolengo 5", 20143, "Milano"));
//		return user;
	}


}

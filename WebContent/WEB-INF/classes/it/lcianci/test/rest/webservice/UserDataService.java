package it.lcianci.test.rest.webservice;

import it.lcianci.test.rest.dao.UserDAO;
import it.lcianci.test.rest.pojo.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 * Questa classe rappresenta il WebService per la gestione CRUD
 * sui singoli oggetti User ma di fatto non viene però esposto all'esterno come
 * WebService di end point dato che non definiamo il path con cui richiamarlo.
 * Sarà lo UsersDataService ad invocarlo all'occorrenza.
 * 
 * @author lcianci
 *
 */
public class UserDataService {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	public UserDataService(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User getUser() {
		User user = UserDAO.myDatabase.getModel().get(id);
		if(user==null)
			throw new RuntimeException("Get: User con " + id +  " non presente in anagrafica");
		return user;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public User getTodoHTML() {
		User user = UserDAO.myDatabase.getModel().get(id);
		if(user==null)
			throw new RuntimeException("Get: User con " + id +  " non presente in anagrafica");
		return user;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putUser(JAXBElement<User> user) {
		User c = user.getValue();
		return putAndGetResponse(c);
	}
	
	@DELETE
	public void deleteUser() {
		User c = UserDAO.myDatabase.getModel().remove(id);
		if(c==null)
			throw new RuntimeException("Delete: User con " + id +  " non presente in anagrafica");
	}
	
	private Response putAndGetResponse(User user) {
		Response res;
		if(UserDAO.myDatabase.getModel().containsKey(user.getId().toString())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		UserDAO.myDatabase.getModel().put(user.getId().toString(), user);
		return res;
	}
	
	

}
package it.lcianci.test.rest.webservice;

import it.lcianci.test.rest.dao.UserDAO;
import it.lcianci.test.rest.pojo.Address;
import it.lcianci.test.rest.pojo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * Questo WebService mi permette di gestire una ipotetica anagrafica utenti
 * con le relative interfacce per le operazioni di CRUD
 * 
 * @author lcianci
 *
 */

@Path("/users")
public class UsersDataService {

	/*
	 * Questa annotation @Context mi permette di caricare all'interno della classe
	 * oggetti esterni come ad esempio: ServletContext, Request, Response, UriInfo
	 */	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	/*
	 * Questi due metodi che seguono restituiscono la lista degli utenti
	 * in anagrafica in formato testo, tipicamente per il browser o in formato
	 * XML/JSON per chiamate applicative
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<User> getUsersBrowser() {
		List<User> users = new ArrayList<User>();
		users.addAll( UserDAO.myDatabase.getModel().values() );
		return users; 
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.addAll( UserDAO.myDatabase.getModel().values() );
		return users; 
	}	
	
	/*
	 * Questo metodo si può pensare come un metodo di interfaccia 
	 * esposto all'esterno per chi chiama il WebService.
	 * Chiamando "../rest/users/count" viene restituito direttamente
	 * il numero di utenti in anagrafica
	 */	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = UserDAO.myDatabase.getModel().size();
		return String.valueOf(count);
	}
	
	/*
	 * Questo metodo consente il salvataggio di nuovi record
	 * a fronte di una chiamata POST attraverso il form di inserimento
	 * dati
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newUser(
			@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("surname") String surname,
			@FormParam("place") String place,
			@FormParam("cap") String cap,
			@FormParam("city") String city,
			@Context HttpServletResponse servletResponse
	) throws IOException {
		User user = new User(new Integer(id),name,surname);
		user.setAddress(new Address(place,new Integer(cap),city));
		
		UserDAO.myDatabase.getModel().put(id, user);
		
		servletResponse.sendRedirect("../create_user.html");
	}
		
	/*
	 * Questo metodo mi permette di definire un path parametrico rappresentato 
	 * in questo caso dall'id utente seguito dal path principale del mio
	 * web service. Quindi in particolare "../rest/users/1" restituirà l'anagrafica
	 * del primo utente passando attraverso il controllo dello UserDataService che è
	 * un WebService interno alla mia applicazione e non esposto come end-point
	 */
	@Path("{id}")
	public UserDataService getUser(
			@PathParam("id") String id) {
		return new UserDataService(uriInfo, request, id);
	}
	
}

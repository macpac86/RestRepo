package it.lcianci.test.rest.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import it.lcianci.test.rest.dao.UserDAO;

/**
 * Questo WebService mi permette di gestire una ipotetica anagrafica utenti
 * con le relative interfacce per le operazioni di CRUD
 * 
 * @author lcianci
 *
 */

@Path("/deleteUsers")
public class DeleteUsersDataService {

	/*
	 * Questa annotation @Context mi permette di caricare all'interno della classe
	 * oggetti esterni come ad esempio: ServletContext, Request, Response, UriInfo
	 */	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*
	 * Questo metodo consente l'eliminazione
	 * a fronte di una chiamata POST attraverso il form di eliminazione
	 * dati
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newUser(
			@FormParam("id") String id,
			@Context HttpServletResponse servletResponse
	) throws IOException {
		
		UserDAO.myDatabase.getModel().remove(id);
		
		servletResponse.sendRedirect("../delete_user.html");
	}
	
	
}

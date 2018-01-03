package it.lcianci.test.rest.client;

import it.lcianci.test.rest.pojo.Address;
import it.lcianci.test.rest.pojo.User;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

/**
 * Questa classe è un semplice client realizzato con un main che effettua tutte le possibili
 * chiamate ai 3 WebServices esposti nella nostra WebApp.
 * 
 * @author lcianci
 *
 */
public class TestClient {
	public static void main(String[] args) {
		final String MY_REST = "rest";
		final String SIMPLE  = "simple";
		final String BIND    = "simpleBind";
		final String USERS   = "users";
		
		
		ClientConfig config = new DefaultClientConfig();
		Client client       = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		WebResource restWS  = service.path(MY_REST);
		
		System.out.println("*************************************************************");
		System.out.println("Chiamata "+SIMPLE);
		System.out.println("*************************************************************");
		System.out.println(restWS.path(SIMPLE).accept(
				MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
		// Get plain text
		System.out.println("-------------------------------------------------------------");
		System.out.println(restWS.path(SIMPLE).accept(
				MediaType.TEXT_PLAIN).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// Get XML
		System.out.println(restWS.path(SIMPLE).accept(
				MediaType.TEXT_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// The HTML
		System.out.println(restWS.path(SIMPLE).accept(
				MediaType.TEXT_HTML).get(String.class));
		System.out.println("-------------------------------------------------------------");

		System.out.println("*************************************************************");
		System.out.println("Chiamata "+BIND);
		System.out.println("*************************************************************");
		// Get XML
		System.out.println(restWS.path(BIND).accept(
				MediaType.TEXT_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// Get XML for application
		System.out.println(restWS.path(BIND).accept(
				MediaType.APPLICATION_JSON).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// Get JSON for application
		System.out.println(restWS.path(BIND).accept(
				MediaType.APPLICATION_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
		
		System.out.println("*************************************************************");
		System.out.println("Chiamata "+USERS);
		System.out.println("*************************************************************");
		
		User user = new User();
		user.setId(3);
		user.setName("Luca");
		user.setSurname("Cianci");
		user.setAddress(new Address("Via XYZ 999", 96100, "Siracusa"));
		ClientResponse response = restWS.path(USERS).path(user.getId().toString()).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, user);
		// Return code should be 201 == created resource
		System.out.println(response.getStatus());
		System.out.println("-------------------------------------------------------------");
		// Get the Users
		System.out.println(restWS.path(USERS).accept(
				MediaType.TEXT_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// Get XML for application
		System.out.println(restWS.path(USERS).accept(
				MediaType.APPLICATION_JSON).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// Get JSON for application
		System.out.println(restWS.path(USERS).accept(
				MediaType.APPLICATION_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// Get the  Todo with id 1
		System.out.println(restWS.path(USERS+"/1").accept(
				MediaType.APPLICATION_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
		// get Todo with id 1 
		restWS.path(USERS+"/3").delete();
		System.out.println("-------------------------------------------------------------");
		// Get the all todos, id 1 should be deleted
		System.out.println(restWS.path(USERS).accept(
				MediaType.APPLICATION_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
		
		// Create a Todo
		Form form = new Form();
		form.add("id", "4");
		form.add("nome", "Paolino");
		form.add("cognome", "Paperino");
		form.add("indirizzo", "Via della scalogna 13");
		form.add("cap", "1317");
		form.add("citta", "Paperopoli");
		response = restWS.path(USERS).type(MediaType.APPLICATION_FORM_URLENCODED)
								   .post(ClientResponse.class, form);
		System.out.println("Form response " + response.getEntity(String.class));
		// Get the all todos, id 4 should be created
		System.out.println(restWS.path(USERS).accept(
				MediaType.APPLICATION_XML).get(String.class));
		System.out.println("-------------------------------------------------------------");
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/RESTJerseyWS").build();
	}

}

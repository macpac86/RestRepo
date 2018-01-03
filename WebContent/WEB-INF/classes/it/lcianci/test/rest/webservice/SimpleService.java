package it.lcianci.test.rest.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Questa classe rappresenta un WebService di tipo REST
 * Grazie alle annotation definisco il path con il quale lo andrò ad invocare (@Path)
 * e il tipo di chiamata che andrò ad intercettare (@Get)
 * Vado anche a definire con @Produces il Content-type da restituire in base
 * ad una richiesta specifica.
 * 
 * In questo caso ho 3 metodi che restituiscono testo, xml o html a seconda di cosa
 * viene richiesto. La chiamata da browser viene sempre gestita con il metodo
 * per HTML
 * 
 * @author lcianci
 *
 */
@Path("/simple")
public class SimpleService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Servizio base: Ciao mondo versione 1.0";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Servizio base: Ciao mondo versione 1.0" + "</hello>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<div><p>"+ "Servizio base: Ciao mondo versione 1.0" + "</p></div>";
	}

}

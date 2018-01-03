package it.lcianci.test.jaxb;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import it.lcianci.test.rest.pojo.Address;
import it.lcianci.test.rest.pojo.User;

public class JAXBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws JAXBException {
		
		ArrayList<Address> al = new ArrayList<Address>();
		al.add(new Address("Via ABC 123", 20100, "Milano2"));
		al.add(new Address("Via ABC 123", 20100, "Milano3"));
		
		User user = new User(1, "Luca", "Cianci");
		user.setAddress(new Address("Via ABC 123", 20100, "Milano"));
		user.setOtherAddress(al);
		
		JAXBContext context = JAXBContext.newInstance(User.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(user, System.out);

	}

}

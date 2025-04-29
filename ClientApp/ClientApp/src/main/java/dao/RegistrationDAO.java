package dao;

import dto.Client;
import dto.ClientDocuments;
import dto.Person;
import dto.Registration;

public class RegistrationDAO {
	
	
	
	
	public static boolean registration(Registration data) {
		Person person = new Person();
		Client client = new Client();
		ClientDocuments clientDocuments = new ClientDocuments();
		
		person.setName(data.getName());
		person.setSurname(data.getSurname());
		person.setUsername(data.getUsername());
		person.setPassword(data.getPassword());
		person.setRole(data.getRole());
		
		int id = PersonDAO.insertPerson(person);
		
		if(id == -1) {
			return false;
		}
		
		client.setEmail(data.getEmail());
		client.setPhone(data.getPhone());
		client.setAvatar(data.getAvatar());
		
		if(ClientDAO.insertClient(client, id)) {
			clientDocuments.setIdCard(data.getIdCard());
			clientDocuments.setPassport(data.getPassport());
			clientDocuments.setDriverLicenseNumber(data.getDriverLicenseNumber());
			ClientDocumentsDAO.insertClientDocuments(clientDocuments, id);
		}
		
		return true;
	}

}

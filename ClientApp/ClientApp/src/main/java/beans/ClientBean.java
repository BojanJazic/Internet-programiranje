package beans;

import java.io.Serializable;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import dao.ClientDAO;
import dao.ClientDocumentsDAO;
import dao.PersonDAO;
import dao.RegistrationDAO;
import dto.Person;
import dto.Registration;

public class ClientBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295422945986092939L;
	
	private Person person = new Person();
	private boolean isLoggedIn = false;
	
	public boolean login(String username, String password) {
		if((person = PersonDAO.checkLogin(username, password)) != null) {
			isLoggedIn = true;
			return true;
		}
		
		return false;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void logout() {
		person = new Person();
		isLoggedIn = false;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public boolean isUsernameAllowed(String username) {
		return PersonDAO.isUserNameUsed(username);
	}
	
	public boolean addClient(Registration registration) {
		return RegistrationDAO.registration(registration);
	}
	
	public boolean updatePassword(String username, String password) {
		return PersonDAO.updatePassword(username, password);
	}
	
	public boolean deactivateAccount(String username) {
		return PersonDAO.deactivateAccount(username);
	}
	
	public boolean checkPassword(String username, String password) {
		return PersonDAO.checkPassword(username, password);
	}

	
	public boolean checkDriverLicense(String username) {
		return ClientDocumentsDAO.checkUserDriverLicense(username);
	}
	
	public int getClientId(String username) {
		return PersonDAO.getClientId(username);
	}
	
	public boolean checkIsClientBlocked(int id) {
		return ClientDAO.checkIsClientBlocked(id);
	}
	
}

package beans;

import java.io.Serializable;
import java.util.List;

import dao.ScooterDAO;
import dto.Vehicle;

public class ScooterBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5908511923992736242L;
	
	public List<Vehicle> getAllScooters(){
		return ScooterDAO.getAllScooters();
	}

}

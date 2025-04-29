package beans;

import java.io.Serializable;
import java.util.List;

import dao.CarDAO;
import dto.Car;
import dto.Vehicle;

public class CarBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5392962801654075573L;
	
	public List<Vehicle> getAllCars(){
		return CarDAO.getAllCars();
	}

}

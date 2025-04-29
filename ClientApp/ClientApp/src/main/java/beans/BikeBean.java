package beans;

import java.io.Serializable;
import java.util.List;

import dao.BikeDAO;
import dto.Vehicle;

public class BikeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2387292392962874260L;
	
	public List<Vehicle> getAllBikes(){
		return BikeDAO.getAllBikes();
	}

}

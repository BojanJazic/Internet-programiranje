package beans;

import java.io.Serializable;

import dao.LocationDAO;
import dto.Location;

public class LocationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9163834496251196521L;
	
	public int insertLocation(Location location) {
		return LocationDAO.insertLocation(location);
	}
	
	

}

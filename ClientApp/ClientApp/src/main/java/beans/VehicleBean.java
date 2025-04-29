package beans;

import java.io.Serializable;

import dao.VehicleDAO;

public class VehicleBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8071878597620218674L;
	
	public boolean rentVehicle(String idVehicle) {
		return VehicleDAO.rentVehicle(idVehicle);
	}
	
	public boolean freeVehicle(String idVehicle) {
		return VehicleDAO.freeVehicle(idVehicle);
	}
	
	public int getVehiclePrice(String idVehicle) {
		return VehicleDAO.getVehiclePrice(idVehicle);
	}

}

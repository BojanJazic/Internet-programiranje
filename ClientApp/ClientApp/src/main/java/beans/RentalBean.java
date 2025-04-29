package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dao.RentalDAO;
import dto.Rental;
import dto.RentalResponse;

public class RentalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7676622506289222349L;

	private List<RentalResponse> rentals = new ArrayList<>();
	
	
	public List<RentalResponse> getRentals(String username){
		rentals = RentalDAO.getRentals(username);
		
		return rentals;
	}
	
	public List<RentalResponse> getRentalsList(){
		return rentals;
	}
	
	public int insertRental(Rental rental) {
		return RentalDAO.insertRental(rental);
	}
	
	
}

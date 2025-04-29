package dto;

import java.time.LocalDateTime;

public class Rental {
	

	
	private int id;
	private int idPerson;
	private String idVehicle;
	private LocalDateTime pickupDateTime;
	private int rentingDuration;
	private int pickupLocation;
	private int dropLocation;
	private int rentalPrice;
	
	
	public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Rental(int idPerson, String idVehicle, LocalDateTime pickupDateTime, int rentingDuration, int pickupLocation,
			int dropLocation, int rentalPrice) {
		super();
		this.idPerson = idPerson;
		this.idVehicle = idVehicle;
		this.pickupDateTime = pickupDateTime;
		this.rentingDuration = rentingDuration;
		this.pickupLocation = pickupLocation;
		this.dropLocation = dropLocation;
		this.rentalPrice = rentalPrice;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdPerson() {
		return idPerson;
	}


	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}


	public String getIdVehicle() {
		return idVehicle;
	}


	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
	}


	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}


	public void setPickupDateTime(LocalDateTime pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}


	public int getRentingDuration() {
		return rentingDuration;
	}


	public void setRentingDuration(int rentingDuration) {
		this.rentingDuration = rentingDuration;
	}


	public int getPickupLocation() {
		return pickupLocation;
	}


	public void setPickupLocation(int pickupLocation) {
		this.pickupLocation = pickupLocation;
	}


	public int getDropLocation() {
		return dropLocation;
	}


	public void setDropLocation(int dropLocation) {
		this.dropLocation = dropLocation;
	}


	public int getRentalPrice() {
		return rentalPrice;
	}


	public void setRentalPrice(int rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	
	
	
	
	

}

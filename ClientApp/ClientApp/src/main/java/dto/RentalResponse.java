package dto;

import java.time.LocalDateTime;

public class RentalResponse {

	private int id;
	private String manufacturer;
	private String model;
	private LocalDateTime pickupDateTime;
	private int rentingDuration;
	private Location pickupLocation;
	private Location dropLocation;
	private int rentalPrice;
	
	
	public RentalResponse(int id, String manufacturer, String model, LocalDateTime pickupDateTime, int rentingDuration,
			Location pickupLocation, Location dropLocation, int rentalPrice) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
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


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
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


	public Location getPickupLocation() {
		return pickupLocation;
	}


	public void setPickupLocation(Location pickupLocation) {
		this.pickupLocation = pickupLocation;
	}


	public Location getDropLocation() {
		return dropLocation;
	}


	public void setDropLocation(Location dropLocation) {
		this.dropLocation = dropLocation;
	}


	public int getRentalPrice() {
		return rentalPrice;
	}


	public void setRentalPrice(int rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	
	
	
	
	
}

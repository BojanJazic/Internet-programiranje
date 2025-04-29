package dto;

public class Vehicle {

	private String idVehicle;
	private String manufacturer;
	private String model;
	
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Vehicle(String idVehicle, String manufacturer, String model) {
		super();
		this.idVehicle = idVehicle;
		this.manufacturer = manufacturer;
		this.model = model;
	}


	public String getIdVehicle() {
		return idVehicle;
	}


	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
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
	
	
}

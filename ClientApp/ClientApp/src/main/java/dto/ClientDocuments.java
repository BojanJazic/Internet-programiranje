package dto;

public class ClientDocuments extends Client {

	private int id;
	 private String idCard;
	 private String passport;
	 private String driverLicenseNumber;
	 
	 
	 
	 public ClientDocuments() {
		super();
		// TODO Auto-generated constructor stub
	 }



	public ClientDocuments(int id, String idCard, String passport, String driverLicenseNumber) {
		super();
		this.id = id;
		this.idCard = idCard;
		this.passport = passport;
		this.driverLicenseNumber = driverLicenseNumber;
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getIdCard() {
		return idCard;
	}



	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}



	public String getPassport() {
		return passport;
	}



	public void setPassport(String passport) {
		this.passport = passport;
	}



	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}



	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}
	 
	
	
	 
	 
	 
	 
	
}

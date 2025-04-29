package dto;

import java.io.Serializable;

public class Registration implements Serializable {
	private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String role;
	private String email;
    private String phone;
    private String avatar;
    private boolean isBlocked;
    private String idCard;
	private String passport;
	private String driverLicenseNumber;
	
	
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Registration(String name, String surname, String username, String password, String email,
			String phone, String avatar, String idCard, String passport,
			String driverLicenseNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.role = "CLIENT";
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.isBlocked = isBlocked;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public boolean isBlocked() {
		return isBlocked;
	}


	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
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

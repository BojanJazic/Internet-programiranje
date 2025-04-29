package dto;

import java.io.Serializable;

public class Client extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1378035334739402668L;
	
	private int id;
	private String email;
    private String phone;
    private String avatar;
    private boolean isBlocked;
    
    
    
    
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Client(String name, String surname, String username, String password, String role) {
		super(name, surname, username, password, role);
		// TODO Auto-generated constructor stub
	}


	public Client(int id, String email, String phone, String avatar, boolean isBlocked) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.isBlocked = isBlocked;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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


	@Override
	public String toString() {
		return "Client " + super.toString() + " [email=" + email + ", phone=" + phone + ", avatar=" + avatar + ", isBlocked=" + isBlocked + "]";
	}
    
    
    
	

}

package dto;

import java.util.Objects;

public class Person {
	private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String role;
    
    
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Person(String name, String surname, String username, String password, String role) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public Person(int id, String name, String surname, String username, String role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.role = role;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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


	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public int hashCode() {
		return Objects.hash(username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + ", username=" + username + ", role=" + role + "]";
	}

	
    
}
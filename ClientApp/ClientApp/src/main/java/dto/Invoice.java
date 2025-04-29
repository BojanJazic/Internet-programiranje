package dto;

public class Invoice {

	private int amount;
	private int id_rental;
	
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Invoice(int amount, int id_rental) {
		super();
		this.amount = amount;
		this.id_rental = id_rental;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getId_rental() {
		return id_rental;
	}


	public void setId_rental(int id_rental) {
		this.id_rental = id_rental;
	}
	
	
	
	
}

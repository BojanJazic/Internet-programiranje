package beans;

import java.io.Serializable;

public class MarketingContentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3298239653930854260L;
	
	private int idPerson;
	private String title;
	
	
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
}

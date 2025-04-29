package dto;

import java.math.BigDecimal;

public class Location {

	private BigDecimal coordinateX;
	private BigDecimal coordinateY;
	
	
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Location(BigDecimal coordinateX, BigDecimal coordinateY) {
		super();
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}


	public BigDecimal getCoordinateX() {
		return coordinateX;
	}


	public void setCoordinateX(BigDecimal coordinateX) {
		this.coordinateX = coordinateX;
	}


	public BigDecimal getCoordinateY() {
		return coordinateY;
	}


	public void setCoordinateY(BigDecimal coordinateY) {
		this.coordinateY = coordinateY;
	}


	@Override
	public String toString() {
		return "[" + coordinateX.toString() + ", " + coordinateY.toString() + "]";
	}
	
	
	
	
}

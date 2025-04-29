package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Car;
import dto.Vehicle;

public class CarDAO {
	
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_CARS = "SELECT v.id_vehicle, m.name, v.model FROM vehicle v "
			+ "JOIN car c ON v.id_vehicle = c.id_vehicle "
			+ "JOIN manufacturer m ON v.id_manufacturer = m.id_manufacturer WHERE v.rental_price > 0 AND v.is_rented = 0 AND v.is_broken = 0";
	
	
	
	public static List<Vehicle> getAllCars(){
		List<Vehicle> cars = new ArrayList<>();
		
		Connection connection = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_CARS);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cars.add(new Car(
							rs.getString("id_vehicle"),
							rs.getString("name"),
							rs.getString("model")
						));
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return cars;
	}

}

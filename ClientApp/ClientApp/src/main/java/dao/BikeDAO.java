package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Car;
import dto.Vehicle;

public class BikeDAO {
	
	
private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_BIKES = "SELECT v.id_vehicle, m.name, v.model FROM vehicle v "
			+ "JOIN e_bike e ON v.id_vehicle = e.id_vehicle "
			+ "JOIN manufacturer m ON v.id_manufacturer = m.id_manufacturer where v.rental_price > 0";
	
	
	
	public static List<Vehicle> getAllBikes(){
		List<Vehicle> bikes = new ArrayList<>();
		
		Connection connection = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_BIKES);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bikes.add(new Car(
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
		
		return bikes;
	}

}

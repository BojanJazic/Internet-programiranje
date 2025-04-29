package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VehicleDAO {
	
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_UPDATE_RENTED_ATTRIBUTE = "UPDATE vehicle set is_rented=1 where id_vehicle = ?";
	private static final String SQL_FREE_VEHICLE = "UPDATE vehicle set is_rented=0 where id_vehicle = ?";
	private static final String SQL_GET_VEHICLE_PRICE = "SELECT rental_price FROM vehicle where id_vehicle = ?";
	
	
	public static boolean rentVehicle(String idVehicle) {
		boolean result = false;
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionPool.checkOut();
			pstmt = connection.prepareStatement(SQL_UPDATE_RENTED_ATTRIBUTE);
			pstmt.setString(1, idVehicle);
			
			pstmt.executeUpdate();
			
			if(pstmt.getUpdateCount() > 0) {
				result = true;
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}
	
	public static boolean freeVehicle(String idVehicle) {
		boolean result = false;
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionPool.checkOut();
			pstmt = connection.prepareStatement(SQL_FREE_VEHICLE);
			pstmt.setString(1, idVehicle);
			
			pstmt.executeUpdate();
			
			if(pstmt.getUpdateCount() > 0) {
				result = true;
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}
	
	public static int getVehiclePrice(String vehicleId) {
		int result = -1;
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionPool.checkOut();
			pstmt = connection.prepareStatement(SQL_GET_VEHICLE_PRICE);
			pstmt.setString(1, vehicleId);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				result = rs.getInt("rental_price");
			}
			
			pstmt.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}

}

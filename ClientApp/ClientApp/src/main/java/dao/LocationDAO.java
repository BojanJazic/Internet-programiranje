package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import dto.Location;
import dto.Rental;

public class LocationDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	
	private static final String SQL_INSERT_LOCATION = "INSERT INTO location (latitude, longitude) VALUES(?, ?)";
	
	
	
	
public static int insertLocation(Location location) {
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Object[] values = {location.getCoordinateX(), location.getCoordinateY() };
		
		try {
			connection = connectionPool.checkOut();
			pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_LOCATION, true, values);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			 // Zatvori ResultSet i PreparedStatement
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        connectionPool.checkIn(connection);
		}
		
		//greska
		return -1;
		
		
	}

}

package dao;

import java.awt.geom.GeneralPath;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import dto.Location;
import dto.Rental;
import dto.RentalResponse;

public class RentalDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_RENTALS = "SELECT m.name, v.model, r.date_time, r.renting_duration, pl.latitude AS pickup_latitude, pl.longitude AS pickup_longitude, dl.latitude AS dropoff_latitude, dl.longitude AS dropoff_longitude, r.rental_price "
	        + "FROM rental r "
	        + "JOIN location pl ON r.pickup_location = pl.id_location "
	        + "JOIN location dl ON r.dropoff_location = dl.id_location "
	        + "JOIN person p ON r.id_person = p.id_person "
	        + "JOIN vehicle v ON r.id_vehicle = v.id_vehicle "
	        + "JOIN manufacturer m ON v.id_manufacturer = m.id_manufacturer "
	        + "WHERE p.username = ?";
	
	private static final String SQL_ADD_NEW_RENTAL = "INSERT INTO rental(id_person, id_vehicle, date_time, renting_duration, pickup_location, dropoff_location, rental_price) VALUES (?,?,?,?,?,?,?)";
	
	
	
	public static List<RentalResponse> getRentals(String username){
		List<RentalResponse> rentals = new ArrayList<>();
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionPool.checkOut();
			pstmt = connection.prepareStatement(SQL_SELECT_RENTALS);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			int i = 1;
			while(rs.next()) {
				rentals.add(new RentalResponse(
						i++,
						rs.getString("name"),
						rs.getString("model"),
						rs.getObject("date_time", LocalDateTime.class),
						rs.getInt("renting_duration"),
						new Location(rs.getBigDecimal("pickup_latitude"),
								rs.getBigDecimal("pickup_longitude"))
						,
						new Location(rs.getBigDecimal("dropoff_latitude"),
								rs.getBigDecimal("dropoff_longitude")),
						rs.getInt("rental_price")
						
						));
			}
			
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		
		return rentals;
		
	}
	
	
	public static int insertRental(Rental rental) {
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Object[] values = {rental.getIdPerson(), rental.getIdVehicle(), rental.getPickupDateTime(), rental.getRentingDuration(), rental.getPickupLocation(), rental.getDropLocation() , rental.getRentalPrice() };
		
		try {
			connection = connectionPool.checkOut();
			pstmt = DAOUtil.prepareStatement(connection, SQL_ADD_NEW_RENTAL, true, values);
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

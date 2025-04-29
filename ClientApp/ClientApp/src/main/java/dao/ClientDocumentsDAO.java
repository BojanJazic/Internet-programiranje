package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Client;
import dto.ClientDocuments;

public class ClientDocumentsDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM person WHERE username=? AND password=?";
	private static final String SQL_INSERT = "INSERT INTO client_documents (id_person, id_card, passport, driver_license_number) VALUES (?,?,?,?)";
	private static final String SQL_CHECK_DRIVER_LICENSE = "SELECT cd.driver_license_number FROM client_documents cd "
															+ "JOIN client c ON cd.id_person = c.id_person "
															+ "JOIN person p ON c.id_person = p.id_person WHERE p.username=?";
	
	
	//potrebno napraviti za cuvanje klijenta, odnosno za registrovanje
	
	public static boolean insertClientDocuments(ClientDocuments clientDocuments, int id) {
		
		boolean result = false;
		
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {id, clientDocuments.getIdCard(), clientDocuments.getPassport(), clientDocuments.getDriverLicenseNumber()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, result, values);
			pstmt.executeUpdate();
			//generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount() > 0) {
				result = true;
			}
			
			
			pstmt.close();
				
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
		
	}
	
	public static boolean checkUserDriverLicense(String username) {
		boolean result = false;
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionPool.checkOut();
			pstmt = connection.prepareStatement(SQL_CHECK_DRIVER_LICENSE);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String driverLicense = rs.getString("driver_license_number");
				
				if(driverLicense != null)
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
	

}

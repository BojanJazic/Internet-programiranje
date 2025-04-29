package service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBean;

public class UserManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5551727503586463508L;

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_CHECK_LOGIN = "SELECT * FROM person WHERE username = ? AND password = ? AND role = 'MANAGER'";
	
	
	public UserBean login(String username, String password) {
		
		String hashedPassword = PasswordHasher.hashPassword(password);
		
		Connection connection = null;
		try {
		    connection = connectionPool.checkOut();
		    try (PreparedStatement pstmt = connection.prepareStatement(SQL_CHECK_LOGIN)) {
		        pstmt.setString(1, username);
		        pstmt.setString(2, hashedPassword);

		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                UserBean userBean = new UserBean();
		                userBean.setIdPerson(rs.getInt("id_person"));
		                userBean.setUsername(rs.getString("username"));
		                userBean.setPassword(rs.getString("password"));
		                userBean.setLoggedIn(true);
		                return userBean;
		            }
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    if (connection != null) {
		        connectionPool.checkIn(connection);
		    }
		}
		return null;
	}
	
	
	
	
}

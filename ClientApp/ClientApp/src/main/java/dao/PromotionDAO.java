package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class PromotionDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_CHECK_IF_PROMOTION_EXISTS = "SELECT * FROM promotion p WHERE p.start_date <= ? AND p.end_date >= ?";
	
	
	public static boolean checkPromotion(Date date) {
		boolean result = false;
		
		
		Connection connection = null;
		
		try {
			connection = connectionPool.checkOut();
			try(PreparedStatement pstmt = connection.prepareStatement(SQL_CHECK_IF_PROMOTION_EXISTS)){
				pstmt.setDate(1, date);
				 pstmt.setDate(2, date);
				
				try(ResultSet rSet = pstmt.executeQuery()){
					if(rSet.next()) {
						result = true;
					}
				}
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connectionPool.checkIn(connection);
			}
		}
		
		return result;
		
	}
	
	
}

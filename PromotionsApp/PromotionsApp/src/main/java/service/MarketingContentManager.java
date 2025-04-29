package service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.MarketingContentBean;

public class MarketingContentManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3417809808626199376L;
	
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT_MARKETING_CONTENT = "INSERT INTO marketing_content(id_person, title) "
																+ "VALUES(?, ?)";
	
	
	
	public int insertNewMarketingContent(MarketingContentBean marketingContentBean) {
		
		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			Object values[] = {marketingContentBean.getIdPerson(), marketingContentBean.getTitle()};
			try(PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_MARKETING_CONTENT, true, values)){
				
				pstmt.executeUpdate();
				
				try(ResultSet rSet = pstmt.getGeneratedKeys()){
					if(rSet.next()) {
						return rSet.getInt(1);
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connectionPool.checkIn(connection);
			}
		}
		
		return -1;
	}
	

}

package dao;

import java.security.cert.TrustAnchor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Invoice;

public class InvoiceDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	
	private static final String INSERT_NEW_INVOICE = "INSERT INTO invoice(amount, id_rental) values(?,?)";
	
	public static int insertInvoice(Invoice invoice) {
		Connection connection = null;
		Object values[] = {invoice.getAmount(), invoice.getId_rental()};
		try {
			connection = connectionPool.checkOut();
			try(PreparedStatement pstmt = DAOUtil.prepareStatement(connection, INSERT_NEW_INVOICE, true, values)){
				pstmt.executeUpdate();
				
				try(ResultSet rSet = pstmt.getGeneratedKeys()){
					if(rSet.next()) {
						return rSet.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connectionPool.checkIn(connection);
			}
		}
		return -1;
	}
	
}

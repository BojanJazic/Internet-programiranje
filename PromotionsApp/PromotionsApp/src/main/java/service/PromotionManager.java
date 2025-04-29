package service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PromotionBean;

public class PromotionManager implements Serializable {
    private static final long serialVersionUID = 3291428072492917740L;
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SQL_GET_ALL_PROMOTIONS = "SELECT mc.title, p.description, p.start_date, p.end_date FROM promotion p "
            + "JOIN marketing_content mc ON p.id_marketing_content = mc.id_marketing_content";

    private static final String SQL_INSERT_NEW_PROMOTION = "INSERT INTO promotion(description, start_date, end_date, id_marketing_content) "
            + "VALUES(?,?,?,?)";

    public List<PromotionBean> getPromotions() {
        List<PromotionBean> promotions = new ArrayList<>();
        Connection connection = null;

        try {
            connection = connectionPool.checkOut();
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL_PROMOTIONS);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PromotionBean promotionBean = new PromotionBean();
                    promotionBean.setTitle(rs.getString("title"));
                    promotionBean.setDescription(rs.getString("description"));
                    promotionBean.setStartDate(rs.getDate("start_date"));
                    promotionBean.setEndDate(rs.getDate("end_date"));
                    promotions.add(promotionBean);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connectionPool.checkIn(connection);
            }
        }
        return promotions;
    }

    public boolean insertNewPromotion(PromotionBean promotionBean) {
        Connection connection = null;
        try {
            connection = connectionPool.checkOut();
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_NEW_PROMOTION)) {
                pstmt.setString(1, promotionBean.getDescription());
                pstmt.setDate(2, new Date(promotionBean.getStartDate().getTime()));
                pstmt.setDate(3, new Date(promotionBean.getEndDate().getTime()));
                pstmt.setInt(4, promotionBean.getIdMarketingContent());

                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connectionPool.checkIn(connection);
            }
        }
        return false;
    }
}

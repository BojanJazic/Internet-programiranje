package service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PostBean;

public class PostManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715535594149084321L;
	
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_GET_ALL_POSTS = "SELECT mc.title, p.content FROM post p "
													+ "JOIN marketing_content mc "
													+ "ON p.id_marketing_content = mc.id_marketing_content";
	
	private static final String SQL_INSERT_NEW_POST = "INSERT INTO post(content, id_marketing_content) VALUES(?,?)";
	
	
	public List<PostBean> getPosts(){
		List<PostBean> posts = new ArrayList<PostBean>();
		
		Connection connection = null;
		
		try {
			connection = connectionPool.checkOut();
			try(PreparedStatement pstmst = connection.prepareStatement(SQL_GET_ALL_POSTS)){
				try(ResultSet rSet = pstmst.executeQuery()){
					while(rSet.next()) {
						PostBean postBean = new PostBean();
						postBean.setTitle(rSet.getString("title"));
						postBean.setContent(rSet.getString("content"));
						
						posts.add(postBean);
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
		
		return posts;
	}
	
	public boolean insertNewPost(PostBean postBean) {
		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			try(PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_NEW_POST)){
				pstmt.setString(1, postBean.getContent());
				pstmt.setInt(2, postBean.getIdMarketingContent());
				
				int rowsAffected = pstmt.executeUpdate();
				
				return rowsAffected == 1;
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connectionPool.checkIn(connection);
			}
		}
		
		return false;
	}

}

<%@page import="service.PostManager"%>
<%@page import="beans.PostBean"%>
<%@page import="beans.MarketingContentBean"%>
<%@page import="service.MarketingContentManager"%>
<%@page import="org.json.JSONObject"%>
<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<%
	UserBean user = (UserBean) session.getAttribute("user");
	if(user == null || !user.isLoggedIn()){
		out.print("error");
		return;
	}
	
	StringBuilder sb = new StringBuilder();
	String line;
	while((line = request.getReader().readLine()) != null){
		sb.append(line);
	}
	
	JSONObject jsonData = new JSONObject(sb.toString());
	
	String title = jsonData.getString("title");
	String content = jsonData.getString("content");
	
	int idManager = user.getIdPerson();
	
	MarketingContentManager marketingContentManager = new MarketingContentManager();
	MarketingContentBean marketingContentBean = new MarketingContentBean();
	marketingContentBean.setIdPerson(idManager);
	marketingContentBean.setTitle(title);
	
	int generatedId = marketingContentManager.insertNewMarketingContent(marketingContentBean);
	
	if(generatedId > 0){
		PostBean postBean = new PostBean();
		postBean.setContent(content);
		postBean.setIdMarketingContent(generatedId);
		
		PostManager postManager = new PostManager();
		boolean result = postManager.insertNewPost(postBean);
		
		if(result){
			out.print("success");
		} else {
			out.print("error");
		}
	}
	
	
	
%>
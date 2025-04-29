<%@page import="service.PromotionManager"%>
<%@page import="java.sql.Date"%>
<%@page import="beans.PromotionBean"%>
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
	String description = jsonData.getString("description");
	String startDateStr = jsonData.getString("startDate");
	String endDateStr = jsonData.getString("endDate");
	
	Date startDate = Date.valueOf(startDateStr); // Format mora biti "yyyy-MM-dd"
    Date endDate = Date.valueOf(endDateStr);
	
	int idManager = user.getIdPerson();
	
	MarketingContentManager marketingContentManager = new MarketingContentManager();
	MarketingContentBean marketingContentBean = new MarketingContentBean();
	marketingContentBean.setIdPerson(idManager);
	marketingContentBean.setTitle(title);
	
	int generatedId = marketingContentManager.insertNewMarketingContent(marketingContentBean);
	
	if(generatedId > 0){
		PromotionBean promotionBean = new PromotionBean();
		promotionBean.setDescription(description);
		promotionBean.setStartDate(startDate);
		promotionBean.setEndDate(endDate);
		promotionBean.setIdMarketingContent(generatedId);
		
		PromotionManager promotionManager = new PromotionManager();
		boolean result = promotionManager.insertNewPromotion(promotionBean);
		
		if(result){
			System.out.println("VRACENI REZULTAT JE TRUE");
			out.print("success");
		} else{
			out.print("error");
		}
		
	}
	
	


%>
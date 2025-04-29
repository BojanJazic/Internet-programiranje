package beans;

import java.io.Serializable;

public class PostBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8889238095929684164L;
	
	private int idPost;
	private String title;
	private String content;
	private int idMarketingContent;
	
	
	
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIdMarketingContent() {
		return idMarketingContent;
	}
	public void setIdMarketingContent(int idMarketingContent) {
		this.idMarketingContent = idMarketingContent;
	}
	
	
	

}

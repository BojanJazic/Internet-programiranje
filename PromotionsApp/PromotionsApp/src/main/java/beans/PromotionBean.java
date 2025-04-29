package beans;

import java.io.Serializable;
import java.sql.Date;

public class PromotionBean implements Serializable {
    private static final long serialVersionUID = -5747866996322984751L;

    private int idPromotion;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int idMarketingContent;

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getIdMarketingContent() {
        return idMarketingContent;
    }

    public void setIdMarketingContent(int idMarketingContent) {
        this.idMarketingContent = idMarketingContent;
    }
}

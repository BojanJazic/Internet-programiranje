package beans;

import java.io.Serializable;

import dao.InvoiceDAO;
import dto.Invoice;

public class InvoiceBean implements Serializable {
	
	
	public int insertInvoice(Invoice invoice) {
		return InvoiceDAO.insertInvoice(invoice);
	}

}

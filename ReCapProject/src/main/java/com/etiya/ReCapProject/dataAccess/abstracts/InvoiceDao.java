package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
	
	List<Invoice> getByRental_Customer_Id(int customerId);

	List<Invoice> getByInvoiceDateBetween(Date startDate,Date endDate);
	
	
}

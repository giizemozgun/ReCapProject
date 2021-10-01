package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateInvoiceRequest;


public interface InvoiceService  {

	DataResult<List<Invoice>> getAll();
	DataResult<Invoice> getById(int id);
	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);

	
	
	DataResult<List<Invoice>> getByCustomerId(int customerId);
	
	DataResult<List<Invoice>> getByInvoiceDateBetween(InvoiceBetweenDateRequest invoiceBetweenDateRequest);
}

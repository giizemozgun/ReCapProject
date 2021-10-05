package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.InvoiceDetailDto;
import com.etiya.ReCapProject.entities.requests.invoice.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.invoice.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.invoice.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.invoice.UpdateInvoiceRequest;


public interface InvoiceService  {

	DataResult<List<InvoiceDetailDto>> getAll();
	DataResult<InvoiceDetailDto> getById(int id);
	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);


	DataResult<List<InvoiceDetailDto>> getByCustomerId(int customerId);
	
	DataResult<List<InvoiceDetailDto>> getByInvoiceDateBetween(InvoiceBetweenDateRequest invoiceBetweenDateRequest);
}

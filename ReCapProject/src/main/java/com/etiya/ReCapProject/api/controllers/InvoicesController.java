package com.etiya.ReCapProject.api.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.requests.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.UpdateInvoiceRequest;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
	
	private InvoiceService invoiceService;
	
	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest ) {
		
	return this.invoiceService.add(createInvoiceRequest);
	}

	
	@GetMapping("/getall")
	public DataResult<List<Invoice>> getAll(){
		
		return this.invoiceService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<Invoice> getById(int invoiceId){
		return this.invoiceService.getById(invoiceId);
	}
	
	@GetMapping("/getbycustomerid")
	public DataResult<List<Invoice>> getByCustomerId(int customerId){
		return this.invoiceService.getByCustomerId(customerId);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
	
	@GetMapping("/getbyinvoicedatebetween")
	public DataResult<List<Invoice>> getByInvoiceDateBetween(String startDate, String endDate) throws ParseException{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1 = dateFormat.parse(endDate);
		Date endDate1 = dateFormat.parse(startDate);

		InvoiceBetweenDateRequest invoiceBetweenDateRequest = new InvoiceBetweenDateRequest();
		invoiceBetweenDateRequest.setEndDate(startDate1);
		invoiceBetweenDateRequest.setStartDate(endDate1);
		
		return this.invoiceService.getByInvoiceDateBetween(invoiceBetweenDateRequest);
	}
	
}
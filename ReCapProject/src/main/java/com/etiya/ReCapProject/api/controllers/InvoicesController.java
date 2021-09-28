package com.etiya.ReCapProject.api.controllers;

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
	
	@PostMapping("/addforindividualcustomer")
	public Result addForIndividualCustomer(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
		
	return this.invoiceService.addForIndividualCustomer(createInvoiceRequest);
	}
	
	@PostMapping("/addforcorporatecustomer")
	public Result addForCorporateustomer(@Valid @RequestBody CreateInvoiceRequest createRentalRequest) {
		
	return this.invoiceService.addForCorporateCustomer(createRentalRequest);
	}
	
	
	
	@GetMapping("/getall")
	public DataResult<List<Invoice>> getAll(){
		
		return this.invoiceService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Invoice> getById(int id){
		return this.invoiceService.getById(id);
	}
	
	@PostMapping("/updateforcorporatecustomer")
	public Result updateForCorporateCustomer(@Valid @RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.updateForCorporateCustomer(updateInvoiceRequest);
	}
	
	@PostMapping("/updateforindividualcustomer")
	public Result updateForIndividualCustomer(@Valid @RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.updateForIndividualCustomer(updateInvoiceRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
}

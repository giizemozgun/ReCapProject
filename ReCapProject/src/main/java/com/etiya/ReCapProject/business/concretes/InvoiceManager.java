package com.etiya.ReCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.InvoiceDao;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.UpdateInvoiceRequest;

@Service
public class InvoiceManager implements InvoiceService {
	private InvoiceDao invoiceDao;
	private RentalService rentalService;


	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, RentalService rentalService) {
		super();
		this.invoiceDao = invoiceDao;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<Invoice>> getAll() {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.findAll());
	}

	@Override
	public DataResult<Invoice> getById(int invoiceId) {
		return new SuccessDataResult<Invoice>(this.invoiceDao.getById(invoiceId));
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Date now = new Date();
		
		Rental rental = this.rentalService.getById(createInvoiceRequest.getRentalId()).getData();

		long totalRentalDay = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),                 
				rental.getReturnDate().toInstant());          
		double totalAmount = rental.getCar().getDailyPrice() * totalRentalDay;         
		
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(createInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(now);
		invoice.setTotalRentalDay((int)totalRentalDay);
		invoice.setTotalAmount(totalAmount);
		invoice.setRental(this.rentalService.getById(createInvoiceRequest.getRentalId()).getData());

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.InvoiceAdded);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Date now = new Date();

		Rental rental = this.rentalService.getById(updateInvoiceRequest.getRentalId()).getData();
		
		long totalRentalDay = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),                 
				rental.getReturnDate().toInstant());          
		double totalAmount = rental.getCar().getDailyPrice() * totalRentalDay; 
		
		Invoice invoice = new Invoice();
		invoice.setInvoiceId(updateInvoiceRequest.getInvoiceId());
		invoice.setInvoiceNumber(updateInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(now);
		invoice.setTotalRentalDay((int)totalRentalDay);
		invoice.setTotalAmount(totalAmount);
		invoice.setRental(this.rentalService.getById(updateInvoiceRequest.getRentalId()).getData());

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.InvoiceUpdated);
	}

	

	@Override
	public DataResult<List<Invoice>> getByCustomerId(int customerId) {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.getByRental_Customer_Id(customerId));
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice invoice = new Invoice();
		invoice.setInvoiceId(deleteInvoiceRequest.getInvoiceId());

		this.invoiceDao.delete(invoice);
		return new SuccessResult(Messages.InvoiceDeleted);
	}

	@Override
	public DataResult<List<Invoice>> getByInvoiceDateBetween(InvoiceBetweenDateRequest invoiceBetweenDateRequest) {
		
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.getByInvoiceDateBetween(invoiceBetweenDateRequest.getStartDate(), invoiceBetweenDateRequest.getEndDate()));
	}

}

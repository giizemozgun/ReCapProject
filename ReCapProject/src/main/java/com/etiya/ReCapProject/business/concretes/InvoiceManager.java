package com.etiya.ReCapProject.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.InvoiceDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.UpdateInvoiceRequest;

@Service
public class InvoiceManager implements InvoiceService {
	
	private InvoiceDao invoiceDao;

	
	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao) {
		super();
		this.invoiceDao = invoiceDao;
	}

	@Override
	public DataResult<List<Invoice>> getAll() {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.findAll());
	}

	@Override
	public DataResult<Invoice> getById(int id) {
		return new SuccessDataResult<Invoice>(this.invoiceDao.getById(id));
	}

	@Override
	public Result addForIndividualCustomer(CreateInvoiceRequest createInvoiceRequest) {
		
		
		Rental rental = new Rental();
		rental.setId(createInvoiceRequest.getRentalId());
	
		Date now = new Date();
		
		Car car = new Car();
		
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(createInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(now);
		invoice.setRental(rental);
		
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.ADD);
			
	}

	@Override
	public Result addForCorporateCustomer(CreateInvoiceRequest createInvoiceRequest) {
		Rental rental = new Rental();
		rental.setId(createInvoiceRequest.getRentalId());
		
		Date rentDate = rental.getRentDate();
		Date returnDate = rental.getReturnDate();
		long totalRentalDay = returnDate.getTime() - rentDate.getTime();
		Date now = new Date();
		
		Car car = new Car();
		double totalAmount = car.getDailyPrice()* totalRentalDay;
		
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(createInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(now);
		invoice.setTotalAmount(totalAmount);
		invoice.setTotalRentalDay((int)totalRentalDay);
		invoice.setRental(rental);
		
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.ADD);
			
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice invoice = new Invoice();
		invoice.setInvoiceId(deleteInvoiceRequest.getInvoiceId());
		
		this.invoiceDao.delete(invoice);
		return new SuccessResult(Messages.DELETE);
	}

	@Override
	public Result updateForCorporateCustomer(UpdateInvoiceRequest updateInvoiceRequest) {
		Rental rental = new Rental();
		rental.setId(updateInvoiceRequest.getRentalId());
		
		Date rentDate = rental.getRentDate();
		Date returnDate = rental.getReturnDate();
		long totalRentalDay = returnDate.getTime() - rentDate.getTime();
		Date now = new Date();
		
		Car car = new Car();
		double totalAmount = car.getDailyPrice()* totalRentalDay;
		
		Invoice invoice = new Invoice();
		invoice.setInvoiceId(updateInvoiceRequest.getInvoiceId());
		invoice.setInvoiceNumber(updateInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(now);
		invoice.setTotalAmount(totalAmount);
		invoice.setTotalRentalDay((int)totalRentalDay);
		invoice.setRental(rental);
		
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.UPDATE);
			
		
	}

	@Override
	public Result updateForIndividualCustomer(UpdateInvoiceRequest updateInvoiceRequest) {
		Rental rental = new Rental();
		rental.setId(updateInvoiceRequest.getRentalId());
		
		Date rentDate = rental.getRentDate();
		Date returnDate = rental.getReturnDate();
		long totalRentalDay = returnDate.getTime() - rentDate.getTime();
		Date now = new Date();
		
		Car car = new Car();
		double totalAmount = car.getDailyPrice()* totalRentalDay;
		
		Invoice invoice = new Invoice();
		invoice.setInvoiceId(updateInvoiceRequest.getInvoiceId());
		invoice.setInvoiceNumber(updateInvoiceRequest.getInvoiceNumber());
		invoice.setInvoiceDate(now);
		invoice.setTotalAmount(totalAmount);
		invoice.setTotalRentalDay((int)totalRentalDay);
		invoice.setRental(rental);
		
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.UPDATE);
	}

	@Override
	public DataResult<List<Invoice>> getByCustomerId(int customerId) {
		
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.getByRental_Customer_Id(customerId));
	}
	
	
}

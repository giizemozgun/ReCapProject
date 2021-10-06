package com.etiya.ReCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.InvoiceDao;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.dtos.InvoiceDetailDto;
import com.etiya.ReCapProject.entities.requests.invoice.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.invoice.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.invoice.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.invoice.UpdateInvoiceRequest;

@Service
public class InvoiceManager implements InvoiceService {
	private InvoiceDao invoiceDao;
	private ModelMapper modelMapper;
	private RentalDao rentalDao;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapper modelMapper, RentalDao rentalDao) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapper = modelMapper;
		this.rentalDao = rentalDao;

	}

	@Override
	public DataResult<List<InvoiceDetailDto>> getAll() {
		List<Invoice> invoices = this.invoiceDao.findAll();
		List<InvoiceDetailDto> invoiceDetailDtos = invoices.stream()
				.map(invoice -> modelMapper.map(invoice, InvoiceDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceDetailDto>>(invoiceDetailDtos, Messages.InvoicesListed);
	}

	@Override
	public DataResult<InvoiceDetailDto> getById(int invoiceId) {
		Invoice invoice = this.invoiceDao.getById(invoiceId);
		InvoiceDetailDto invoiceDetailDto = modelMapper.map(invoice, InvoiceDetailDto.class);

		return new SuccessDataResult<InvoiceDetailDto>(invoiceDetailDto, Messages.GetInvoice);
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Date now = new Date();

		Rental rental = this.rentalDao.getById(createInvoiceRequest.getRentalId());

		long totalRentalDay = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),
				rental.getReturnDate().toInstant());
		double totalAmount = rental.getTotalAmount();

		Invoice invoice = modelMapper.map(createInvoiceRequest, Invoice.class);
		invoice.setInvoiceDate(now);
		invoice.setTotalRentalDay((int) totalRentalDay);
		invoice.setTotalAmount(totalAmount);

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.InvoiceAdded);
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {

		Invoice invoice = modelMapper.map(deleteInvoiceRequest, Invoice.class);

		this.invoiceDao.delete(invoice);
		return new SuccessResult(Messages.InvoiceDeleted);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Date now = new Date();

		Rental rental = this.rentalDao.getById(updateInvoiceRequest.getRentalId());

		long totalRentalDay = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),
				rental.getReturnDate().toInstant());
		double totalAmount = rental.getCar().getDailyPrice() * totalRentalDay;

		Invoice invoice = modelMapper.map(updateInvoiceRequest, Invoice.class);
		invoice.setInvoiceDate(now);
		invoice.setTotalRentalDay((int) totalRentalDay);
		invoice.setTotalAmount(totalAmount);

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.InvoiceUpdated);
	}

	@Override
	public DataResult<List<InvoiceDetailDto>> getByCustomerId(int customerId) {
		List<Invoice> invoices = this.invoiceDao.getByRental_Customer_Id(customerId);

		List<InvoiceDetailDto> invoiceDetailDtos = invoices.stream()
				.map(invoice -> modelMapper.map(invoice, InvoiceDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceDetailDto>>(invoiceDetailDtos, Messages.InvoicesOfCustomerListed);
	}

	@Override
	public DataResult<List<InvoiceDetailDto>> getByInvoiceDateBetween(
			InvoiceBetweenDateRequest invoiceBetweenDateRequest) {
		List<Invoice> invoices = this.invoiceDao.getByInvoiceDateBetween(invoiceBetweenDateRequest.getStartDate(),
				invoiceBetweenDateRequest.getEndDate());

		List<InvoiceDetailDto> invoiceDetailDtos = invoices.stream()
				.map(invoice -> modelMapper.map(invoice, InvoiceDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceDetailDto>>(invoiceDetailDtos, Messages.BetweenDatesInvoices);
	}

}

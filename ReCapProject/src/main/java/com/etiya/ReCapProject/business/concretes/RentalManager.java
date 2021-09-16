package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Rental;
@Service
public class RentalManager implements RentalService{

	private RentalDao rentalDao;
	
	@Autowired
	public RentalManager(RentalDao rentalDao) {
		super();
		this.rentalDao = rentalDao;
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll());
	}

	@Override
	public DataResult<Rental> getById(int rentalId) {
		return new SuccessDataResult<Rental> (this.rentalDao.getById(rentalId));
	}

	@Override
	public Result add(Rental rental) {
		
		var result=BusinessRules.run(checkReturn(rental.getCar().getCarId()));
		
		if (result!=null) {
			return result; 
		}
		
		this.rentalDao.save(rental);
		return new SuccessResult("Kiralama eklendi");
	}

	@Override
	public Result delete(Rental rental) {
		this.rentalDao.delete(rental);
		return new SuccessResult("Kiralama silindi");
	}

	@Override
	public Result update(Rental rental) {
		this.rentalDao.save(rental);
		return new SuccessResult("Kiralama güncellendi");
	}
	
	private Result checkReturn(int carId) {
		for(Rental rental : this.rentalDao.getByCar_CarId(carId)) {
			if(rental.getReturnDate()==null) {
				return new ErrorResult("Araç teslim edilmedi.");
			}
		}
		return new SuccessResult();
		
		
	}
}
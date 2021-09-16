package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Rental;

public interface RentalService {
	
	DataResult<List<Rental>> getAll();
	DataResult<Rental> getById(int rentalId);
	Result add(Rental rental);
	Result delete(Rental rental);
	Result update(Rental rental);
	
	
}

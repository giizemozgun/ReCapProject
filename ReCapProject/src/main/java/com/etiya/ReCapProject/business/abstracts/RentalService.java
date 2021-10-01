package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateRentalRequest;

public interface RentalService {
	
	DataResult<List<Rental>> getAll();
	DataResult<Rental> getById(int rentalId);
	Result addForIndividualCustomer(CreateRentalRequest createRentalRequest);
	Result addForCorporateCustomer(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result updateForCorporateCustomer(UpdateRentalRequest updateRentalRequest);
	Result updateForIndividualCustomer(UpdateRentalRequest updateRentalRequest);
	
	Result validateCarReturned (CarReturnedRequest carReturnedRequest);
	
	
	

}

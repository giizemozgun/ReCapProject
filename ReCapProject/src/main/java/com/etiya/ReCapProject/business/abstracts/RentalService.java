package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.RentalDetailDto;
import com.etiya.ReCapProject.entities.requests.car.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.rental.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.rental.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.rental.UpdateRentalRequest;

public interface RentalService {
	
	DataResult<List<RentalDetailDto>> getAll();
	DataResult<RentalDetailDto> getById(int id);
	Result addForIndividualCustomer(CreateRentalRequest createRentalRequest);
	Result addForCorporateCustomer(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result updateForCorporateCustomer(UpdateRentalRequest updateRentalRequest);
	Result updateForIndividualCustomer(UpdateRentalRequest updateRentalRequest);
	Result validateCarReturned (CarReturnedRequest carReturnedRequest);
	
	
}

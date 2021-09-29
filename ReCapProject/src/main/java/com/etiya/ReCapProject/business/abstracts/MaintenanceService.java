package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Maintenance;
import com.etiya.ReCapProject.entities.requests.CarReturnedFromMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.CreateMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.DeleteMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.UpdateMaintenanceRequest;

public interface MaintenanceService {
	
	DataResult<List<Maintenance>> getAll();
	DataResult<Maintenance> getById(int id);
	Result add(CreateMaintenanceRequest createMaintenanceRequest);
	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);
	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);
	
	Result validateCarReturned (CarReturnedFromMaintenanceRequest carReturnedFromMaintenanceRequest);
	
}

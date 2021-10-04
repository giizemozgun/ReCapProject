package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.MaintenanceDetailDto;
import com.etiya.ReCapProject.entities.requests.CarReturnedFromMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateMaintenanceRequest;

public interface MaintenanceService {
	
	DataResult<List<MaintenanceDetailDto>> getAll();
	DataResult<MaintenanceDetailDto> getById(int id);
	Result add(CreateMaintenanceRequest createMaintenanceRequest);
	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);
	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);
	
	Result validateCarReturned (CarReturnedFromMaintenanceRequest carReturnedFromMaintenanceRequest);
	
}

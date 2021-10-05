package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.ColorDetailDto;
import com.etiya.ReCapProject.entities.requests.color.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.color.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.color.UpdateColorRequest;

public interface ColorService {

	DataResult<List<ColorDetailDto>> getAll();
	DataResult<ColorDetailDto> getById(int colorId);
	Result add(CreateColorRequest createColorRequest);
	Result delete(DeleteColorRequest deleteColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
}

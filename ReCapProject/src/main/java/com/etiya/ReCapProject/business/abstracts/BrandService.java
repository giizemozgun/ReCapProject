package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.BrandDetailDto;
import com.etiya.ReCapProject.entities.requests.brand.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.brand.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.brand.UpdateBrandRequest;

public interface BrandService {

	DataResult<List<BrandDetailDto>> getAll();
	DataResult<BrandDetailDto> getById(int brandId);
	Result add(CreateBrandRequest createBrandrequest);
	Result delete(DeleteBrandRequest deleteBrandrequest);
	Result update(UpdateBrandRequest updateBrandrequest);
}

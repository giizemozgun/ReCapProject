package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Brand;

public interface BrandService {

	DataResult<List<Brand>> getAll();
	DataResult<Brand> getById(int brandId);
	Result add(Brand brand);
	Result delete(Brand brand);
	Result update(Brand brand);
}

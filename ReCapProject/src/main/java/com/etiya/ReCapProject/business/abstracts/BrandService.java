package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.entities.concretes.Brand;

public interface BrandService {

	List<Brand> getAll();
	Brand getById(int brandId);
	void add(Brand brand);
	void delete(Brand brand);
	void update(Brand brand);
}

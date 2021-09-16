package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;

	@Autowired
	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public List<Brand> getAll() {
		return this.brandDao.findAll();
	}

	@Override
	public Brand getById(int brandId) {
		return this.brandDao.getById(brandId);
	}

	@Override
	public void add(Brand brand) {
		this.brandDao.save(brand);
		
	}

	@Override
	public void delete(Brand brand) {
		this.brandDao.delete(brand);
		
	}

	@Override
	public void update(Brand brand) {
		
		this.brandDao.save(brand);
	}
	
}

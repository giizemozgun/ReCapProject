package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
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
	public DataResult<List<Brand>> getAll() {
		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll());
	}

	@Override
	public DataResult<Brand> getById(int brandId) {
		return new SuccessDataResult<Brand> (this.brandDao.getById(brandId));
	}

	@Override
	public Result add(Brand brand) {
		this.brandDao.save(brand);
		return new SuccessResult("Marka eklendi");
	}

	@Override
	public Result delete(Brand brand) {
		this.brandDao.delete(brand);
		return new SuccessResult("Marka silindi");
		
	}

	@Override
	public Result update(Brand brand) {
		
		this.brandDao.save(brand);
		return new SuccessResult("Marka güncellendi");
	}
	
}

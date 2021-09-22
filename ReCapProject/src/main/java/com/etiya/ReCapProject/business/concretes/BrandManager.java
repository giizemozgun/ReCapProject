package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.requests.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.UpdateBrandRequest;

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
	public Result add(CreateBrandRequest createBrandrequest) {	
		Brand brand = new Brand();
		brand.setBrandName(createBrandrequest.getBrandName());
		
		this.brandDao.save(brand);
		return new SuccessResult( Messages.BRAND +" " +  Messages.ADD);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandrequest) {
		
		Brand brand = new Brand();
		brand.setBrandId(deleteBrandrequest.getBrandId());
		
		this.brandDao.delete(brand);
		return new SuccessResult( Messages.BRAND + " " +  Messages.DELETE);
		
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandrequest) {
		Brand brand = new Brand();
		brand.setBrandName(updateBrandrequest.getBrandName());
		brand.setBrandId(updateBrandrequest.getBrandId());
		
		this.brandDao.save(brand);
		return new SuccessResult(Messages.BRAND + " " +  Messages.UPDATE);
	}


	
}

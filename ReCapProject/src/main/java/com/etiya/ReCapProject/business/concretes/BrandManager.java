package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.dtos.BrandDetailDto;
import com.etiya.ReCapProject.entities.requests.brand.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.brand.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.brand.UpdateBrandRequest;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;
	private ModelMapper modelMapper;

	@Autowired
	public BrandManager(BrandDao brandDao,ModelMapper modelMapper) {
		super();
		this.brandDao = brandDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<BrandDetailDto>> getAll() {
		
		 List<Brand> brands= this.brandDao.findAll();
		 
		 List<BrandDetailDto> brandDetailDtos=brands.stream().map(brand -> modelMapper.map(brand, BrandDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<BrandDetailDto>>(brandDetailDtos);
	}

	@Override
	public DataResult<BrandDetailDto> getById(int brandId) {
		Brand brand = this.brandDao.getById(brandId);
		BrandDetailDto brandDetailDto = modelMapper.map(brand,BrandDetailDto.class);
		
		return new SuccessDataResult<BrandDetailDto>(brandDetailDto);
	}

	@Override
	public Result add(CreateBrandRequest createBrandrequest) {	
		
		var result = BusinessRules.run(checkBrandNameDuplication(createBrandrequest.getBrandName()));

		if (result != null) {
			return result;
		}
				
		Brand brand = modelMapper.map(createBrandrequest, Brand.class);
		
		this.brandDao.save(brand);
		return new SuccessResult( Messages.BrandAdded);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandrequest) {
		
		Brand brand = modelMapper.map(deleteBrandrequest, Brand.class);
		
		this.brandDao.delete(brand);
		return new SuccessResult( Messages.BrandDeleted);
		
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandrequest) {
		var result = BusinessRules.run(checkBrandNameDuplication(updateBrandrequest.getBrandName()));

		if (result != null) {
			return result;
		}
		
		Brand brand = modelMapper.map(updateBrandrequest, Brand.class);
		
		this.brandDao.save(brand);
		return new SuccessResult(Messages.BrandUpdated);
	}
	
	private Result checkBrandNameDuplication(String brandName) {

		if (this.brandDao.existsByBrandName(brandName)) {
			return new ErrorResult(Messages.ExistBrand);
		}
		return new SuccessResult();

	}
	

	
}

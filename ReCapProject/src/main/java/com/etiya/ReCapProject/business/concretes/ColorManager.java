package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.dtos.ColorDetailDto;
import com.etiya.ReCapProject.entities.requests.color.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.color.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.color.UpdateColorRequest;

@Service
public class ColorManager implements ColorService{
	private ColorDao colorDao;
	private ModelMapper modelMapper;

	@Autowired
	public ColorManager(ColorDao colorDao,ModelMapper modelMapper ) {
		super();
		this.colorDao = colorDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<ColorDetailDto>> getAll() {
		List<Color> colors= this.colorDao.findAll();
		 
		 List<ColorDetailDto> colorDetailDtos= colors.stream().map(color -> modelMapper.map(color, ColorDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ColorDetailDto>>(colorDetailDtos);
	}

	@Override
	public DataResult<ColorDetailDto> getById(int colorId) {
		Color color = this.colorDao.getById(colorId);
		ColorDetailDto colorDetailDto = modelMapper.map(color,ColorDetailDto.class);
		
		return new SuccessDataResult<ColorDetailDto>(colorDetailDto);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		
		var result = BusinessRules.run(checkColorNameDuplication(createColorRequest.getColorName()));

		if (result != null) {
			return result;
		}
			
		Color color = new Color();
		color.setColorName(createColorRequest.getColorName());
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.ColorAdded);
		
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		
		Color color = new Color();
		color.setColorId(deleteColorRequest.getColorId());
		
		this.colorDao.delete(color);
		return new SuccessResult(Messages.ColorDeleted);
		
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		var result = BusinessRules.run(checkColorNameDuplication(updateColorRequest.getColorName()));

		if (result != null) {
			return result;
		}
		
		Color color = new Color();
		color.setColorId(updateColorRequest.getColorId());
		color.setColorName(updateColorRequest.getColorName());
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.ColorUpdated);
	}
	
	private Result checkColorNameDuplication(String colorName) {

		if (this.colorDao.existsByColorName(colorName)) {
			return new ErrorResult(Messages.ExistColor);
		}
		return new SuccessResult();

	}
	
}

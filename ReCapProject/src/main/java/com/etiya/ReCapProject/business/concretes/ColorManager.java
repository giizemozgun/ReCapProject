package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.requests.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.UpdateColorRequest;

@Service
public class ColorManager implements ColorService{
	private ColorDao colorDao;

	@Autowired
	public ColorManager(ColorDao colorDao) {
		super();
		this.colorDao = colorDao;
	}

	@Override
	public DataResult<List<Color>> getAll() {
		
		return new SuccessDataResult<List<Color>>(this.colorDao.findAll());
	}

	@Override
	public DataResult<Color> getById(int colorId) {
		return new SuccessDataResult<Color>(this.colorDao.getById(colorId));
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
			
		Color color = new Color();
		color.setColorName(createColorRequest.getColorName());
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.COLOR + " " + Messages.ADD);
		
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		
		Color color = new Color();
		color.setColorId(deleteColorRequest.getColorId());
		color.setColorName(deleteColorRequest.getColorName());	
		
		this.colorDao.delete(color);
		return new SuccessResult(Messages.COLOR + " " + Messages.DELETE);
		
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		Color color = new Color();
		color.setColorId(updateColorRequest.getColorId());
		color.setColorName(updateColorRequest.getColorName());
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.COLOR + " " + Messages.UPDATE);
	}
	
}

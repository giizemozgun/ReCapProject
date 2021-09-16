package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.ReCapProject.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{
	private ColorDao colorDao;

	@Autowired
	public ColorManager(ColorDao colorDao) {
		super();
		this.colorDao = colorDao;
	}

	@Override
	public List<Color> getAll() {
		
		return this.colorDao.findAll();
	}

	@Override
	public Color getById(int colorId) {
		return this.colorDao.getById(colorId);
	}

	@Override
	public void add(Color color) {
		this.colorDao.save(color);
		
	}

	@Override
	public void delete(Color color) {
		this.colorDao.delete(color);
		
	}

	@Override
	public void update(Color color) {
		this.colorDao.save(color);
		
	}
	
}

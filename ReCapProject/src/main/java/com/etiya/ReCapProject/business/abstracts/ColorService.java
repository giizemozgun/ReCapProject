package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.entities.concretes.Color;

public interface ColorService {

	List<Color> getAll();
	Color getById(int colorId);
	void add(Color color);
	void delete(Color color);
	void update(Color color);
}

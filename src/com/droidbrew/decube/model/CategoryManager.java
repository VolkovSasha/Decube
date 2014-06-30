package com.droidbrew.decube.model;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

public class CategoryManager {
	private Dao<Category, Integer> categoryDao = null;
	
	public Dao<Category, Integer> getCategoryDao() {
		return categoryDao;
	}

	public void setDataCategoryDao(Dao<Category, Integer> dataDao) {
		this.categoryDao = dataDao;
	}

	public List<Category> getDataCategory() throws SQLException {
		return categoryDao.queryForAll();
	}

	public CategoryManager() {
		super();
	}

	public void removeCategoryAtId(int id) throws SQLException {
		categoryDao.deleteById(id);

	}

	public Category findCategoryById(int id) throws SQLException {
		return categoryDao.queryForId(id);
	}
}

package com.droidbrew.decube;

import java.sql.SQLException;

import com.droidbrew.decube.db.DecubeAppDBHelper;
import com.droidbrew.decube.model.Category;
import com.droidbrew.decube.model.CategoryManager;
import com.droidbrew.decube.model.ItemCategory;
import com.droidbrew.decube.model.ItemManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import android.app.Application;

public class DecubeApp extends Application{

	private CategoryManager categoryManager = null;
	private ItemManager itemManager = null;
	private DecubeAppDBHelper dbHelper = null;

	public DecubeApp(){
		super();
		try {
			dbHelper = new DecubeAppDBHelper(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CategoryManager getCategoryManager() {
		if(categoryManager == null){
			categoryManager = new CategoryManager();
			try {
				Dao<Category, Integer> categoryDao = DaoManager.createDao(dbHelper.getConnectionSource(), Category.class);
				categoryManager.setDataCategoryDao(categoryDao);

				if(categoryDao.countOf() == 0){
					categoryDao.create(new Category("���"));
					categoryDao.create(new Category("���������"));
					categoryDao.create(new Category("�������"));
					categoryDao.create(new Category("�����"));
					categoryDao.create(new Category("�������"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return categoryManager;
	}

	public ItemManager getItemManager() {
		if(itemManager == null){
			itemManager = new ItemManager();
			try {
				Dao<ItemCategory, Integer> itemDao = DaoManager.createDao(dbHelper.getConnectionSource(), ItemCategory.class);
				itemManager.setDataItemDao(itemDao);

				if(itemDao.countOf() == 0){
					itemDao.create(new ItemCategory(1, "����"));
					itemDao.create(new ItemCategory(1, "���"));
					itemDao.create(new ItemCategory(1, "����"));
					
					itemDao.create(new ItemCategory(2, "�������"));
					itemDao.create(new ItemCategory(2, "���������"));
					itemDao.create(new ItemCategory(2, "������"));
					
					itemDao.create(new ItemCategory(3, "�����"));
					itemDao.create(new ItemCategory(3, "�����"));
					itemDao.create(new ItemCategory(3, "�������"));
					
					itemDao.create(new ItemCategory(4, "�����"));
					itemDao.create(new ItemCategory(4, "�������"));
					itemDao.create(new ItemCategory(4, "����������"));
					
					itemDao.create(new ItemCategory(5, "����"));
					itemDao.create(new ItemCategory(5, "����"));
					itemDao.create(new ItemCategory(5, "�������"));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return itemManager;
	}

}
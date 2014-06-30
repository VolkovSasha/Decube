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
					categoryDao.create(new Category("Еда"));
					categoryDao.create(new Category("Транспорт"));
					categoryDao.create(new Category("Покупка"));
					categoryDao.create(new Category("Отель"));
					categoryDao.create(new Category("Веселье"));
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
					itemDao.create(new ItemCategory(1, "Хлеб"));
					itemDao.create(new ItemCategory(1, "Суп"));
					itemDao.create(new ItemCategory(1, "Борщ"));
					
					itemDao.create(new ItemCategory(2, "Автобус"));
					itemDao.create(new ItemCategory(2, "Велосипед"));
					itemDao.create(new ItemCategory(2, "Машина"));
					
					itemDao.create(new ItemCategory(3, "Майка"));
					itemDao.create(new ItemCategory(3, "Туфли"));
					itemDao.create(new ItemCategory(3, "Рубашка"));
					
					itemDao.create(new ItemCategory(4, "Прага"));
					itemDao.create(new ItemCategory(4, "Ужгород"));
					itemDao.create(new ItemCategory(4, "Закарпатье"));
					
					itemDao.create(new ItemCategory(5, "Кино"));
					itemDao.create(new ItemCategory(5, "Парк"));
					itemDao.create(new ItemCategory(5, "Билиард"));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return itemManager;
	}

}
package com.droidbrew.decube.spec;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import android.util.Log;

import com.droidbrew.decube.model.Category;
import com.droidbrew.decube.model.CaregoryManager;
import com.droidbrew.decube.spec.db.TestDbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DecubeCategoryManagerSpec {

	static CaregoryManager dm = null;
	static ConnectionSource connectionSource = null;
	static Dao<Category, Integer> categoryDao = null;

	@BeforeClass
	public static void setUpDatabaseLayer() throws SQLException {
		connectionSource = new TestDbHelper().getConnectionSource();
		TableUtils.createTableIfNotExists(connectionSource, Category.class);

		dm = new CaregoryManager();

		try {
			categoryDao = DaoManager.createDao(connectionSource, Category.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void clearData() {
		try {
			TableUtils.clearTable(connectionSource, Category.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCategoryDataManager() {
		Category data1 = new Category(1, "Hotel");
		Category data2 = new Category(2, "Food");
		
		try {
			categoryDao.create(data1);
			categoryDao.create(data2);
			dm.setDataCategoryDao(categoryDao);
			
			assertEquals("Hotel", dm.getDataCategory().get(0).getCategory());
			assertEquals("Food", dm.getDataCategory().get(1).getCategory());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeCategoryDataManagerAtId() {
		Category data1 = new Category(1, "Hotel");
		Category data2 = new Category(2, "Food");
		
		try {
			categoryDao.create(data1);
			categoryDao.create(data2);
			dm.setDataCategoryDao(categoryDao);
			dm.removeCategoryAtId(data1.getId());
			dm.removeCategoryAtId(data2.getId());
			assertEquals(0, dm.getDataCategory().size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

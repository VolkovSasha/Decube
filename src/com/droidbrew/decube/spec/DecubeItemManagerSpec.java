package com.droidbrew.decube.spec;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.droidbrew.decube.model.CategoryManager;
import com.droidbrew.decube.model.Category;
import com.droidbrew.decube.model.ItemCategory;
import com.droidbrew.decube.model.ItemManager;
import com.droidbrew.decube.spec.db.TestDbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DecubeItemManagerSpec {
	static ItemManager im = null;
	static ConnectionSource connectionSource = null;
	static Dao<ItemCategory, Integer> itemDao = null;

	@BeforeClass
	public static void setUpDatabaseLayer() throws SQLException {
		connectionSource = new TestDbHelper().getConnectionSource();
		TableUtils.createTableIfNotExists(connectionSource, ItemCategory.class);

		im = new ItemManager();

		try {
			itemDao = DaoManager.createDao(connectionSource, ItemCategory.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void clearData() {
		try {
			TableUtils.clearTable(connectionSource, ItemCategory.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getItemDataManager() {
		ItemCategory data1 = new ItemCategory(1, "OldContinent");
		ItemCategory data2 = new ItemCategory(2, "Sandwich");
		
		try {
			itemDao.create(data1);
			itemDao.create(data2);
			im.setDataItemDao(itemDao);
			
			assertEquals("OldContinent", im.getDataItem().get(0).getItem());
			assertEquals("Sandwich", im.getDataItem().get(1).getItem());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

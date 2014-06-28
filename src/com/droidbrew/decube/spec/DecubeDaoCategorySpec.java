package com.droidbrew.decube.spec;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.droidbrew.decube.model.Category;
import com.droidbrew.decube.model.CaregoryManager;
import com.droidbrew.decube.spec.db.TestDbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DecubeDaoCategorySpec {
	static Dao<Category, Integer> categoryDao = null;
	static ConnectionSource connectionSource = null;

	@BeforeClass
    public static void setUpDatabaseLayer() throws SQLException {
        connectionSource = new TestDbHelper().getConnectionSource();
        TableUtils.createTableIfNotExists(connectionSource, Category.class);
       
		try {
			categoryDao = DaoManager.createDao(connectionSource, Category.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

	@Before
	public void clearData(){
		try {
			TableUtils.clearTable(connectionSource, Category.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void canBeStoredInDB () {
		Category data1 = new Category(1, "Hotel");
		Category data2 = new Category(2, "Food");
		Category read_data1 = null, read_data2 = null;
		try {
			categoryDao.create(data1);
			categoryDao.create(data2);
			
			read_data1 = categoryDao.queryForId(1);
			read_data2 = categoryDao.queryForId(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals("Hotel", read_data1.getCategory());
		assertEquals("Food", read_data2.getCategory());
	}

}

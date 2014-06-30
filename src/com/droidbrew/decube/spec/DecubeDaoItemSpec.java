package com.droidbrew.decube.spec;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.droidbrew.decube.model.Category;
import com.droidbrew.decube.model.ItemCategory;
import com.droidbrew.decube.spec.db.TestDbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DecubeDaoItemSpec {
	static Dao<ItemCategory, Integer> itemDao = null;
	static ConnectionSource connectionSource = null;

	@BeforeClass
    public static void setUpDatabaseLayer() throws SQLException {
        connectionSource = new TestDbHelper().getConnectionSource();
        TableUtils.createTableIfNotExists(connectionSource, ItemCategory.class);
       
		try {
			itemDao = DaoManager.createDao(connectionSource, ItemCategory.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

	@Before
	public void clearData(){
		try {
			TableUtils.clearTable(connectionSource, ItemCategory.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void canBeStoredInDB () {
		ItemCategory data1 = new ItemCategory(1, "OldContinent");
		ItemCategory data2 = new ItemCategory(2, "Sandwich");
		ItemCategory read_data1 = null, read_data2 = null;
		try {
			itemDao.create(data1);
			itemDao.create(data2);
			
			read_data1 = itemDao.queryForId(1);
			read_data2 = itemDao.queryForId(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals("OldContinent", read_data1.getItem());
		assertEquals("Sandwich", read_data2.getItem());
	}
}

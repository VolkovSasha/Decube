package com.droidbrew.decube.model;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

public class ItemManager {
	private Dao<ItemCategory, Integer> itemDao = null;

	public void setDataItemDao(Dao<ItemCategory, Integer> itemDao) {
		this.itemDao = itemDao;
	}

	public List<ItemCategory> getDataItem() throws SQLException {
		return itemDao.queryForAll();
	}

	public ItemManager() {
		super();
	}

	public void removeItemCategoryAtId(int id) throws SQLException {
		itemDao.deleteById(id);

	}

	public ItemCategory findItemById(int id) throws SQLException {
		return itemDao.queryForId(id);
	}
}

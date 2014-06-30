package com.droidbrew.decube.model;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

public class ItemManager {
	private Dao<ItemCategory, Integer> itemDao = null;
	
	public Dao<ItemCategory, Integer> getItemDao() {
		return itemDao;
	}

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

	public List<ItemCategory> findItemByCatId(int idCat) throws SQLException{
		List<ItemCategory> result = null;
		result = getItemDao().queryBuilder().where().eq("idCat", idCat).query();
		return result;
	}
	
	public void removeByCatId(int idCat) throws SQLException{
		DeleteBuilder<ItemCategory, Integer> db = itemDao.deleteBuilder();
		db.where().eq("idCat", idCat);
		db.delete();
	}
}

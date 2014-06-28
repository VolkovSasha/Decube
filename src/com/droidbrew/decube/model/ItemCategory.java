package com.droidbrew.decube.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "item_category")
public class ItemCategory {
	@DatabaseField(id = true, canBeNull = false)
	private Integer id;
	@DatabaseField(columnName = "idCat", canBeNull = false, index = true, indexName = "idCat_index")
	private Integer idCat;
	@DatabaseField(columnName = "item", canBeNull = false, index = true, indexName = "item_index")
	private String item;

	public ItemCategory() {
		super();
	}

	public ItemCategory(Integer id, Integer idCat, String item) {
		super();
		this.id = id;
		this.idCat = idCat;
		this.item = item;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdCat() {
		return idCat;
	}

	public void setIdCat(Integer idCat) {
		this.idCat = idCat;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
}

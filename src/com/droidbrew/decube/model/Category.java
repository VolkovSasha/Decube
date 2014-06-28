package com.droidbrew.decube.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "category")
public class Category {
	@DatabaseField(id = true, canBeNull = false)
	private Integer id;
	@DatabaseField(columnName = "category", canBeNull = false, index = true, indexName = "category_index")
	private String category;

	public Category() {
		super();
	}

	public Category(Integer id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}

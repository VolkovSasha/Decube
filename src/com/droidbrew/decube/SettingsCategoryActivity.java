package com.droidbrew.decube;

import java.sql.SQLException;

import com.droidbrew.decube.adapter.CategoryAdapter;
import com.droidbrew.decube.model.CategoryManager;
import com.droidbrew.decube.model.ItemCategory;
import com.droidbrew.decube.model.ItemManager;
import com.j256.ormlite.dao.Dao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class SettingsCategoryActivity extends Activity {
	CategoryManager categoryManager;
	ItemManager itemManager;
	ListView categoryList = null;
	CategoryAdapter catAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_category);
		setTitle(getString(R.string.activity_title_settings_category));

		initListCategory();
	}

	public void initListCategory() {
		categoryManager = ((DecubeApp) getApplication()).getCategoryManager();
		itemManager = ((DecubeApp)getApplication()).getItemManager();
		try {
			catAdapter = new CategoryAdapter(getApplicationContext(),
					categoryManager.getDataCategory());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		categoryList = (ListView) findViewById(R.id.category_list);
		categoryList.setAdapter(catAdapter);

		categoryList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				Intent intent = new Intent(SettingsCategoryActivity.this, SettingsItemCategoryActivity.class);
				intent.putExtra("categoryId", (int)id);
				startActivity(intent);
			}
		});
		
		categoryList.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> l, View v,
					int position, final long id) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsCategoryActivity.this);
				dialog.setTitle("Delete question?");
				dialog.setPositiveButton("Delete", new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						try {
							categoryManager.removeCategoryAtId((int)id);
							itemManager.removeByCatId((int)id);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							catAdapter = new CategoryAdapter(getApplicationContext(), categoryManager.getDataCategory());
							categoryList.setAdapter(catAdapter);
							Intent intent = new Intent(SettingsCategoryActivity.this, HomeActivity.class);
							startActivity(intent);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
				dialog.setNegativeButton("Cancel", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				dialog.show();

				return true;
			}
		});
	}
}

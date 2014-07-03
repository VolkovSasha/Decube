package com.droidbrew.decube;

import java.sql.SQLException;

import com.droidbrew.decube.adapter.ItemCategoryAdapter;
import com.droidbrew.decube.model.ItemManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SettingsItemCategoryActivity extends Activity {
	ItemManager itemManager;
	ListView itemList = null;
	ItemCategoryAdapter itemAdapter;
	private int catId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_item_category);
		
		initListItemCategory();
	}
	
	public void initListItemCategory(){
		catId = getIntent().getIntExtra("categoryId", 0);
		
		itemManager = ((DecubeApp) getApplication()).getItemManager();

		try {
			itemAdapter = new ItemCategoryAdapter(getApplicationContext(),
					itemManager.findItemByCatId(catId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		itemList = (ListView)findViewById(R.id.category_item_list);
		itemList.setAdapter(itemAdapter);
		itemList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					final long id) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsItemCategoryActivity.this);
				dialog.setTitle("Delete answer?");
				dialog.setPositiveButton("Delete", new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						try {
							itemManager.removeItemCategoryAtId((int)id);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							itemAdapter = new ItemCategoryAdapter(getApplicationContext(), itemManager.findItemByCatId(catId));
							itemList.setAdapter(itemAdapter);
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
			}
		});
	}
	
}

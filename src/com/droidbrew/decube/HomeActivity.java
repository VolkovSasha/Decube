package com.droidbrew.decube;

import java.sql.SQLException;

import com.droidbrew.decube.adapter.CategoryAdapter;
import com.droidbrew.decube.fragment.CategoryFragment;
import com.droidbrew.decube.model.Category;
import com.droidbrew.decube.model.CategoryManager;
import com.droidbrew.decube.model.ItemCategory;
import com.j256.ormlite.dao.Dao;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity {
	View dlg = null;
	CategoryManager categoryManager;
	ListView categoryList = null;
	CategoryAdapter catAdapter;
	FragmentManager fm;
	CategoryFragment catFragment = null;
	private Dao<Category, Integer> categoryDao = null;
	private Dao<ItemCategory, Integer> itemDao = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		fm = getSupportFragmentManager();
		catFragment = (CategoryFragment) fm
				.findFragmentById(R.id.fragment_category);
		categoryDao = ((DecubeApp) getApplication()).getCategoryManager()
				.getCategoryDao();
		itemDao = ((DecubeApp)getApplication()).getItemManager().getItemDao();
	}

	public void addNewCategory() {
		final EditText category = new EditText(this);
		category.setHint("Enter a question");
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Add new question");
		dialog.setMessage("Add question");
		dialog.setView(category);
		dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if (category.getText().toString().equals("")) {
					return;
				}
				try {
					categoryDao.create(new Category(category.getText()
							.toString()));

				} catch (SQLException e) {
					e.printStackTrace();
				}
				catFragment.getActivity().recreate();
			}
		});
		dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	public void addNewItem(){
		LayoutInflater inflater = this.getLayoutInflater();
		dlg = inflater.inflate(R.layout.dialog_add_item_for_category, null);
		final Spinner spinner = (Spinner)dlg.findViewById(R.id.spinner_add_item);
		final EditText etext = (EditText)dlg.findViewById(R.id.input_new_item);
		Button btnOk = (Button)dlg.findViewById(R.id.btn_add_new_item);
		Button btnCancel = (Button)dlg.findViewById(R.id.btn_cancel_new_item);
		
		final Dialog dialog = new Dialog(HomeActivity.this);
		dialog.setContentView(dlg);
		dialog.setTitle("Add new answer");
		etext.setHint("Enter new answer");
		
		try {
			categoryManager = ((DecubeApp)getApplication()).getCategoryManager();
			catAdapter = new CategoryAdapter(getApplicationContext(),
					categoryManager.getDataCategory());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		spinner.setAdapter(catAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		      @Override
		      public void onItemSelected(AdapterView<?> parent, View view,
		          int position, long id) {
		      }
		      @Override
		      public void onNothingSelected(AdapterView<?> arg0) {
		      }
		    });
		    
		btnOk.setOnClickListener(new View.OnClickListener() {

			@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
			public void onClick(View v) {
				if (etext.getText().toString().equals("")) {
					return;
				}
				try {
					itemDao.create(new ItemCategory((int)spinner.getSelectedItemId(),etext.getText()
							.toString()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				catFragment.getActivity().recreate();
				dialog.dismiss();
			}
		});
		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_new_category:
			addNewCategory();
			break;

		case R.id.menu_add_item_category:
			addNewItem();
			break;

		case R.id.menu_settings:
			Intent intent = new Intent(this, SettingsCategoryActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}
}

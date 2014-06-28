package com.droidbrew.decube;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    
    public void addNewCategory(){
    	EditText category = new EditText(this); 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	dialog.setTitle("Add new category");
    	dialog.setMessage("Add category");
    	dialog.setView(category);
    	dialog.setPositiveButton("Add", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
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
			break;
		}
		return true;
	}
}

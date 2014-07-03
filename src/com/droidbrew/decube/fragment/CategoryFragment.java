package com.droidbrew.decube.fragment;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.droidbrew.decube.DecubeApp;
import com.droidbrew.decube.R;
import com.droidbrew.decube.adapter.CategoryAdapter;
import com.droidbrew.decube.model.CategoryManager;
import com.droidbrew.decube.model.ItemCategory;
import com.droidbrew.decube.model.ItemManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CategoryFragment extends Fragment implements SensorEventListener {
	CategoryAdapter catAdapter;
	ItemManager itemManager;
	CategoryManager categoryManager;
	Spinner spinner = null;
	TextView randomTV;
	List<ItemCategory> randomList;
	ItemCategory[] array;
	View view;
	int random;
	Animation anim = null;
	ImageView imgCube;
	AnimationDrawable anim1;
	public boolean currentSpin = false;

	private SensorManager sensorManager;
	private long lastUpdate;
	private boolean rand = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.category_fragment, container, false);
		imgCube = (ImageView) view.findViewById(R.id.img_cube);
		imgCube.setBackgroundResource(R.drawable.anim_cube);
		sensorManager = (SensorManager) this.getActivity().getSystemService(
				Activity.SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		lastUpdate = System.currentTimeMillis();

		anim = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.text_combo);
		anim1 = (AnimationDrawable) imgCube.getBackground();
		randomTV = (TextView) view.findViewById(R.id.random_tv);
		itemManager = ((DecubeApp) getActivity().getApplication())
				.getItemManager();
		categoryManager = ((DecubeApp) getActivity().getApplication())
				.getCategoryManager();
		try {
			catAdapter = new CategoryAdapter(getActivity()
					.getApplicationContext(), categoryManager.getDataCategory());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		spinner = (Spinner) view.findViewById(R.id.spinner_category);
		spinner.setAdapter(catAdapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					randomList = itemManager.findItemByCatId((int) id);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				array = randomList.toArray(new ItemCategory[randomList.size()]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		imgCube.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (spinner.getAdapter().getCount() == 0) {
					return;
				}
				if (array.length == 0) {
					return;
				}
				spinCoin1();
				random = (int) (Math.random() * array.length);
				randomTV.setText(array[random].getItem());
				randomTV.startAnimation(anim);

			}
		});
		return view;
	}

	public void spinCoin1() {
		Spin spin = new Spin();
		spin.execute();

	}

	class Spin extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(2500);
				Log.d("sleep", "2000");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			anim1.start();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			anim1.stop();
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (spinner.getAdapter().getCount() == 0) {
			return;
		}
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float[] values = event.values;
			float x = values[0];
			float y = values[1];
			float z = values[2];
			float accelationSquareRoot = (x * x + y * y + z * z)
					/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
			long actualTime = System.currentTimeMillis();
			if (accelationSquareRoot >= 3) {
				if (actualTime - lastUpdate < 1300) {
					return;
				}
				if (array.length == 0) {
					return;
				}
				lastUpdate = actualTime;
				if (rand) {
					random = (int) (Math.random() * array.length);
					randomTV.setText(array[random].getItem());
					randomTV.startAnimation(anim);
					spinCoin1();

				} else {
					random = (int) (Math.random() * array.length);
					randomTV.setText(array[random].getItem());
					randomTV.startAnimation(anim);
					spinCoin1();
				}
				rand = !rand;
			}
		}

	}
}

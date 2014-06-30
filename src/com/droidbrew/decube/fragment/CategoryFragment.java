package com.droidbrew.decube.fragment;

import java.sql.SQLException;

import com.droidbrew.decube.DecubeApp;
import com.droidbrew.decube.R;
import com.droidbrew.decube.adapter.CategoryAdapter;
import com.droidbrew.decube.model.CategoryManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

public class CategoryFragment extends Fragment{
	CategoryAdapter catAdapter;
	CategoryManager categoryManager;
	Spinner spinner = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.category_fragment, container, false);
		
		categoryManager = ((DecubeApp)getActivity().getApplication()).getCategoryManager();
		try {
			catAdapter = new CategoryAdapter(getActivity().getApplicationContext(),
					categoryManager.getDataCategory());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		spinner = (Spinner)view.findViewById(R.id.spinner_category);
		spinner.setAdapter(catAdapter);
		
		return view;
	}
}

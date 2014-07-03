package com.droidbrew.decube.adapter;

import java.util.List;

import com.droidbrew.decube.R;
import com.droidbrew.decube.model.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
	private List<Category> cat;
	private LayoutInflater inflater;

	private class ViewHolder {
		public TextView tv;
	}

	public CategoryAdapter(Context context, List<Category> cat) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.cat = cat;
	}

	@Override
	public int getCount() {
		if (cat != null)
			return cat.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (cat != null && position >= 0 && position < getCount())
			return cat.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (cat != null && position >= 0 && position < getCount())
			return cat.get(position).getId();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder viewHolder;

		if (view == null) {
			view = inflater.inflate(R.layout.category_for_adapter, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.tv = (TextView) view.findViewById(R.id.textView1);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		Category record = cat.get(position);

		viewHolder.tv.setText("" + record.getCategory());
		return view;
	}

}
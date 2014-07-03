package com.droidbrew.decube.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.droidbrew.decube.R;
import com.droidbrew.decube.model.ItemCategory;

public class ItemCategoryAdapter extends BaseAdapter {
	private List<ItemCategory> itemCat;
	private LayoutInflater inflater;

	private class ViewHolder {
		public TextView tv;
	}

	public ItemCategoryAdapter(Context context, List<ItemCategory> itemCat) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.itemCat = itemCat;
	}

	@Override
	public int getCount() {
		if (itemCat != null)
			return itemCat.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (itemCat != null && position >= 0 && position < getCount())
			return itemCat.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (itemCat != null && position >= 0 && position < getCount())
			return itemCat.get(position).getId();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder viewHolder;

		if (view == null) {
			view = inflater.inflate(R.layout.item_category_for_adapter, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.tv = (TextView) view.findViewById(R.id.itemTextView);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		ItemCategory record = itemCat.get(position);

		viewHolder.tv.setText("" + record.getItem());
		return view;
	}

}
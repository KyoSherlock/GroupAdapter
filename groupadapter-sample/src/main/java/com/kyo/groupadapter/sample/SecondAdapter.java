package com.kyo.groupadapter.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jianghui on 4/29/16.
 */
public class SecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	public static final int VIEW_TYPE_BLUE = 0;
	public static final int VIEW_TYPE_PURPLE = 1;

	@NonNull
	private final List<Item> items;
	private final View.OnClickListener onClickListener;

	public SecondAdapter(@NonNull List<Item> items, View.OnClickListener onClickListener) {
		if (items == null) {
			throw new NullPointerException();
		}
		this.items = items;
		this.onClickListener = onClickListener;
	}

	public void addItem(int position, Item item) {
		Log.e("kyo", "addItem position:" + position);
		items.add(position, item);
		this.notifyItemInserted(position);
	}

	@Override
	public int getItemViewType(int position) {
		return items.get(position).viewType;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		if (viewType == VIEW_TYPE_BLUE) {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second_blue, parent, false);
			BlueViewHolder viewHolder = new BlueViewHolder(itemView);
			itemView.setOnClickListener(onClickListener);
			return viewHolder;
		} else {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second_purple, parent, false);
			PurpleViewHolder viewHolder = new PurpleViewHolder(itemView);
			itemView.setOnClickListener(onClickListener);
			return viewHolder;
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof BlueViewHolder) {
			BlueViewHolder viewHolder = (BlueViewHolder) holder;
			viewHolder.itemView.setTag(items.get(position).text);
			viewHolder.textView.setText(items.get(position).text);
		} else {
			PurpleViewHolder viewHolder = (PurpleViewHolder) holder;
			viewHolder.itemView.setTag(items.get(position).text);
			viewHolder.textView.setText(items.get(position).text);
		}
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	static class BlueViewHolder extends RecyclerView.ViewHolder {

		private TextView textView;

		public BlueViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.text);
		}
	}

	static class PurpleViewHolder extends RecyclerView.ViewHolder {
		private TextView textView;

		public PurpleViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.text);
		}
	}

	static class Item {
		String text;
		int viewType;

		public Item(String text) {
			this(text, VIEW_TYPE_BLUE);
		}

		public Item(String text, int viewType) {
			this.text = text;
			this.viewType = viewType;
		}
	}
}
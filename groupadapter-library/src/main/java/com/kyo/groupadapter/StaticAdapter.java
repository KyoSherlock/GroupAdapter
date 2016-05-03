package com.kyo.groupadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jianghui on 5/3/16.
 */
public final class StaticAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private final View itemView;

	public StaticAdapter(@NonNull View itemView) {
		this.itemView = itemView;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new StaticViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		// do nothing
	}

	@Override
	public int getItemCount() {
		return 1;
	}


	static class StaticViewHolder extends RecyclerView.ViewHolder {

		public StaticViewHolder(View itemView) {
			super(itemView);
		}
	}
}

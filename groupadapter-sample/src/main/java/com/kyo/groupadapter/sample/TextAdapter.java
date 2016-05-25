/**
 * Copyright 2015, KyoSherlock
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kyo.groupadapter.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jianghui on 5/3/16.
 */
public class TextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private final List<String> items;
	private View.OnClickListener onClickListener;

	public TextAdapter(List<String> items, View.OnClickListener onClickListener) {
		this.items = items;
		this.onClickListener = onClickListener;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
		itemView.setOnClickListener(onClickListener);
		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		MyViewHolder viewHolder = (MyViewHolder) holder;
		viewHolder.textView.setText(items.get(position));
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	static class MyViewHolder extends RecyclerView.ViewHolder {

		TextView textView;

		public MyViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.text);
		}
	}
}
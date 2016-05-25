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

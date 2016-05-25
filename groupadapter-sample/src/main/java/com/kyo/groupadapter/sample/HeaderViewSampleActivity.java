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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kyo.groupadapter.HeaderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghui on 5/3/16.
 */
public class HeaderViewSampleActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private HeaderAdapter headerAdapter;
	private TextAdapter textAdapter;

	public static Intent createIntent(Context context) {
		Intent intent = new Intent(context, HeaderViewSampleActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.recyclerview);
		recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		/**
		 * important
		 * step-1: Initialize RecyclerView with LayoutManager.
		 * step-2: Initialize the header view with RecyclerView.
		 * purpose: Apply the LayoutParams to the header view.
		 */
		LayoutInflater inflater = LayoutInflater.from(this);
		TextView headerView1 = (TextView) inflater.inflate(R.layout.item_text, recyclerView, false);
		headerView1.setText("Header View 1");
		TextView headerView2 = (TextView) inflater.inflate(R.layout.item_text, recyclerView, false);
		headerView2.setText("Header View 2");
		TextView footerView1 = (TextView) inflater.inflate(R.layout.item_text, recyclerView, false);
		footerView1.setText("Footer View 1");
		TextView footerView2 = (TextView) inflater.inflate(R.layout.item_text, recyclerView, false);
		footerView2.setText("Footer View 2");

		List<String> items = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			items.add(String.valueOf(i));
		}
		textAdapter = new TextAdapter(items, onClickListener);

		HeaderAdapter.Builder builder = new HeaderAdapter.Builder();
		builder.addHeader(headerView1);
		builder.addHeader(headerView2);
		builder.add(textAdapter);
		builder.addFooter(footerView1);
		builder.addFooter(footerView2);
		headerAdapter = builder.build();
		recyclerView.setAdapter(headerAdapter);
	}

	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			/**
			 * In most cases, we don't care the position & we just get the data bound by view.
			 * If we want to get the position for child adapter, we can use {@link com.kyo.groupadapter.GroupAdapter#getChildAdapterStartPosition(RecyclerView.Adapter)}
			 * to calculate.
			 */
			int position = recyclerView.getChildAdapterPosition(v);
			int childAdapterStartPosition = headerAdapter.getChildAdapterStartPosition(textAdapter);
			int childPosition = position - childAdapterStartPosition;
			Toast.makeText(getApplicationContext(), String.valueOf(childPosition), Toast.LENGTH_SHORT).show();
		}
	};
}

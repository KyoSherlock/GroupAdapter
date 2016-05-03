package com.kyo.groupadapter.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.kyo.groupadapter.GroupAdapter;
import com.kyo.groupadapter.StaticAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghui on 5/3/16.
 */
public class GroupAdapterSampleActivity extends AppCompatActivity {

	private static final String TAG = "GroupAdapter";
	private RecyclerView recyclerView;
	private GroupAdapter groupAdapter;
	private FirstAdapter firstAdapter;
	private SecondAdapter secondAdapter;

	public static Intent createIntent(Context context) {
		Intent intent = new Intent(context, GroupAdapterSampleActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recyclerview);
		recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		List<FirstAdapter.Item> firstData = new ArrayList<>();
		firstData.add(new FirstAdapter.Item("1111"));
		firstData.add(new FirstAdapter.Item("2222"));
		firstData.add(new FirstAdapter.Item("3333"));
		firstData.add(new FirstAdapter.Item("4444", FirstAdapter.VIEW_TYPE_PURPLE));
		firstAdapter = new FirstAdapter(firstData, onClickListener);

		List<SecondAdapter.Item> secondData = new ArrayList<>();
		secondData.add(new SecondAdapter.Item("AAAA"));
		secondData.add(new SecondAdapter.Item("BBBB"));
		secondData.add(new SecondAdapter.Item("CCCC"));
		secondData.add(new SecondAdapter.Item("DDDD"));
		secondData.add(new SecondAdapter.Item("EEEE", SecondAdapter.VIEW_TYPE_PURPLE));
		secondAdapter = new SecondAdapter(secondData, onClickListener);


		GroupAdapter.Builder builder = new GroupAdapter.Builder();
		builder.add(firstAdapter);
		builder.add(new StaticAdapter(LayoutInflater.from(this).inflate(R.layout.item_divider, recyclerView, false)));
		builder.add(secondAdapter);
		groupAdapter = builder.build();
		recyclerView.setAdapter(groupAdapter);
	}


	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.item_view: {
					Object obj = v.getTag();
					if (obj instanceof String) {
						String text = (String) obj;
						Log.d(TAG, "clicked text:" + text);
					}
					RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(v);
					int position = viewHolder.getAdapterPosition(); // position in GroupAdapter
					if (position == 0) {
						recyclerView.post(new Runnable() {
							@Override
							public void run() {
								recyclerView.scrollToPosition(0);
							}
						});
					}

					/**
					 * Insert new item to child Adapter.
					 * In most cases, we don't care the position & we just get the data bound by view.
					 * If we want to get the position for child adapter, we can use {@link com.kyo.groupadapter.GroupAdapter#getChildAdapterStartPosition(RecyclerView.Adapter)}
					 * to calculate.
					 */
					if (viewHolder instanceof FirstAdapter.RedViewHolder) {
						int adapterStartPosition = groupAdapter.getChildAdapterStartPosition(firstAdapter); // start position for child adapter
						int childPosition = position - adapterStartPosition;
						FirstAdapter.Item item = new FirstAdapter.Item("new added item");
						firstAdapter.addItem(childPosition, item);
					} else if (viewHolder instanceof FirstAdapter.PurpleViewHolder) {
						int adapterStartPosition = groupAdapter.getChildAdapterStartPosition(firstAdapter); // start position for child adapter
						int childPosition = position - adapterStartPosition;
						FirstAdapter.Item item = new FirstAdapter.Item("new added item", FirstAdapter.VIEW_TYPE_PURPLE);
						firstAdapter.addItem(childPosition, item);
					} else if (viewHolder instanceof SecondAdapter.BlueViewHolder) {
						int adapterStartPosition = groupAdapter.getChildAdapterStartPosition(secondAdapter); // start position for child adapter
						int childPosition = position - adapterStartPosition;
						SecondAdapter.Item item = new SecondAdapter.Item("new added item");
						secondAdapter.addItem(childPosition, item);
					} else if (viewHolder instanceof SecondAdapter.PurpleViewHolder) {
						int adapterStartPosition = groupAdapter.getChildAdapterStartPosition(secondAdapter); // start position for child adapter
						int childPosition = position - adapterStartPosition;
						SecondAdapter.Item item = new SecondAdapter.Item("new added item", SecondAdapter.VIEW_TYPE_PURPLE);
						secondAdapter.addItem(childPosition, item);
					}
					break;
				}
			}
		}
	};
}

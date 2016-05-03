package com.kyo.groupadapter.sample;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyo.groupadapter.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recyclerview);

		List<String> items = new ArrayList<>();
		items.add("GroupAdapter sample");
		items.add("HeaderAdapter sample");
		TextAdapter adapter = new TextAdapter(items, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = recyclerView.getChildAdapterPosition(v);
				if (position == 0) {
					ActivityCompat.startActivity(MainActivity.this, GroupAdapterSampleActivity.createIntent(MainActivity.this), null);
				} else {
					ActivityCompat.startActivity(MainActivity.this, HeaderViewSampleActivity.createIntent(MainActivity.this), null);
				}
			}
		});

		recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}
}

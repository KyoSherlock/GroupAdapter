package com.kyo.groupadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghui on 4/29/16.
 */
public class HeaderAdapter extends GroupAdapter {

	public HeaderAdapter(@NonNull Builder builder) {
		super(builder);
	}

	public static class Builder extends GroupAdapter.Builder {

		final List<RecyclerView.Adapter> headers = new ArrayList<>();
		final List<RecyclerView.Adapter> footers = new ArrayList<>();

		public void addHeader(@NonNull RecyclerView.Adapter adapter) {
			if (adapter == null) {
				throw new NullPointerException();
			}
			headers.add(adapter);
		}

		public void addHeader(@NonNull View view) {
			if (view == null) {
				throw new NullPointerException();
			}
			headers.add(new StaticAdapter(view));
		}

		public void addFooter(@NonNull RecyclerView.Adapter adapter) {
			if (adapter == null) {
				throw new NullPointerException();
			}
			footers.add(adapter);
		}

		public void addFooter(@NonNull View view) {
			if (view == null) {
				throw new NullPointerException();
			}
			footers.add(new StaticAdapter(view));
		}

		@Override
		public HeaderAdapter build() {
			List<RecyclerView.Adapter> adapters = new ArrayList<>();
			adapters.addAll(this.headers);
			adapters.addAll(this.adapters);
			adapters.addAll(this.footers);
			this.adapters = adapters;
			return new HeaderAdapter(this);
		}
	}
}

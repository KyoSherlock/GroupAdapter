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

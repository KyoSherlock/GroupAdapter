# GroupAdapter 

A specialized RecyclerView.Adapter that presents data from a sequence of RecyclerView.Adapter. The sequence is static but each adapter can be presented in zero or more item views. The child adapter can use ViewType safely. In addition, we can addHeaderView or addFooterView like ListView.

# Usage

Below is an example of GroupAdapter.

```java
	GroupAdapter.Builder builder = new GroupAdapter.Builder();
	builder.add(firstAdapter);
	builder.add(secondAdapter);
	GroupAdapter groupAdapter = builder.build();
	recyclerView.setAdapter(groupAdapter);
```
Note: In most cases, we don't care the position & we just get the data bound by view. If we want to get the position for child adapter, we can use groupAdapter.getChildAdapterStartPosition() to calculate.

```java
	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int position = recyclerView.getChildAdapterPosition(v);
			int childAdapterStartPosition = groupAdapter.getChildAdapterStartPosition(childAdapter);
			int childPosition = position - childAdapterStartPosition;
			Toast.makeText(getApplicationContext(), String.valueOf(childPosition), Toast.LENGTH_SHORT).show();
		}
	};
```
Sometimes, we want to use RecyclerView with addHeader or addFooter like ListView. HeaderAdapter is what we want. Actually, it only extends a builder that can addHeader or addFooter.

```java
	/**
	 * Because we need the LayoutParams for the header view.
	 * step-1: Initialize RecyclerView with LayoutManager.
	 * step-2: Initialize the header view with RecyclerView.
	 */
	LayoutInflater inflater = LayoutInflater.from(this);
	TextView headerView = (TextView) inflater.inflate(R.layout.item_text, recyclerView, false);
	headerView.setText("Header View");
	TextView footerView = (TextView) inflater.inflate(R.layout.item_text, recyclerView, false);
	footerView.setText("Footer View");

	HeaderAdapter.Builder builder = new HeaderAdapter.Builder();
	builder.addHeader(headerView);
	builder.add(textAdapter);
	builder.addFooter(footerView);
	headerAdapter = builder.build();
	reyclerView.setAdapter(headerAdapter);
```

# Changelog

### Version: 1.0
  * Initial Build
  
# License

    Copyright 2015, KyoSherlock
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
package com.martin.updroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ArticlesAdapter extends BaseAdapter {

	private NewsCollection nColl;
	private TextView tvTitle, tvContent;
	private static LayoutInflater inflater;

	public ArticlesAdapter(Context context, NewsCollection nColl) {
		super();
		this.nColl = nColl;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return nColl.getTitles().length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View vi = arg1;
		if (vi == null)
			vi = inflater.inflate(R.layout.article_item, null);
		tvTitle = (TextView) vi.findViewById(R.id.tvTitle);
		tvContent = (TextView) vi.findViewById(R.id.tvContent);
		tvTitle.setText(nColl.getTitles()[arg0]);
		tvContent.setText(nColl.getContents()[arg0]);
		return vi;
	}

}

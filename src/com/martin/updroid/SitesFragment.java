package com.martin.updroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SitesFragment extends Fragment implements OnItemClickListener {

	private ListView lvArticles;
	private NewsSources nSources;
	private NewsCollection nColl;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sites, container, false);
		lvArticles = (ListView) view.findViewById(R.id.lvArticles);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		nSources = new NewsSources(getActivity());
		nColl = nSources.getA3_News();

		lvArticles.setAdapter(new ArticlesAdapter(getActivity(), nColl));
		lvArticles.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String url = nColl.getUrls()[arg2];
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
}

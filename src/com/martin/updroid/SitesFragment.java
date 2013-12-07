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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class SitesFragment extends Fragment implements OnItemClickListener,
		OnItemSelectedListener {

	private ListView lvArticles;
	private Spinner spSources;
	private NewsSources nSources;
	private NewsCollection nColl;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sites, container, false);
		lvArticles = (ListView) view.findViewById(R.id.lvArticles);
		spSources = (Spinner) view.findViewById(R.id.spSources);
		spSources.setOnItemSelectedListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		nSources = new NewsSources(getActivity());
		lvArticles.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String url = nColl.getUrls()[arg2];
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		switch (arg2) {
		case 0:
			nColl = nSources.getA3_News();
			lvArticles.setAdapter(new ArticlesAdapter(getActivity(), nColl));
			break;
		case 1:
			nColl = nSources.getA3_Devhub();
			lvArticles.setAdapter(new ArticlesAdapter(getActivity(), nColl));
			break;
		case 2:
			nColl = nSources.getSpaceEngineers_News();
			lvArticles.setAdapter(new ArticlesAdapter(getActivity(), nColl));
			break;
		case 3:
			nColl = nSources.getLayer_News();
			lvArticles.setAdapter(new ArticlesAdapter(getActivity(), nColl));
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

}

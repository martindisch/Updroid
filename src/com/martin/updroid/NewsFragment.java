package com.martin.updroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsFragment extends Fragment implements OnItemClickListener {
	OnProgressChangeListener mCallback;
	
	private ListView lvArticles;
	private NewsSources nSources;
	private NewsCollection nColl;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.news, container, false);
		lvArticles = (ListView) view.findViewById(R.id.lvArticles);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		nSources = new NewsSources(getActivity());
		lvArticles.setOnItemClickListener(this);
		loadUnread();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnProgressChangeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnProgressChangeListener");
        }
	}

	public void loadUnread() {
		if (mCallback.changeVisibility()) {
			getActivity().setProgressBarIndeterminateVisibility(true);
		}
		mCallback.actionStarted();
		new Thread(new Runnable() {

			@Override
			public void run() {
				nColl = nSources.getAllUnread();
				lvArticles.post(new Runnable() {

					@Override
					public void run() {
						lvArticles.setAdapter(new ArticlesAdapter(getActivity(),
								nColl));
						if (nColl.getTitles().length == 0) {
							Toast.makeText(getActivity(), "No news", Toast.LENGTH_SHORT).show();
						}
						mCallback.actionFinished();
						if (mCallback.changeVisibility()) {
							getActivity().setProgressBarIndeterminateVisibility(false);
						}
					}
					
				});
			}
			
		}).start();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String url = nColl.getUrls()[arg2];
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
		SharedPreferences spLibrary = getActivity().getSharedPreferences("Library", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = spLibrary.edit();
		editor.putBoolean(((TextView) arg1.findViewById(R.id.tvTitle)).getText().toString(), true);
		editor.commit();
		loadUnread();
	}
	
}

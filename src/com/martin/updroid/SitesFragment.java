package com.martin.updroid;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class SitesFragment extends Fragment implements OnClickListener {

	private Button bStart;
	private LinearLayout llContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sites, container, false);
		bStart = (Button) view.findViewById(R.id.bStart);
		bStart.setOnClickListener(this);
		llContent = (LinearLayout) view.findViewById(R.id.llContent);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		WebView wvEntry;
		String sContent;
		Util util = new Util(getActivity());
		/*Resources r = getActivity().getResources();
		LayoutParams params = new LayoutParams(
		        LayoutParams.WRAP_CONTENT,      
		        LayoutParams.WRAP_CONTENT
		);
		params.setMargins(0, 0, 0, (int) TypedValue.applyDimension(
		        TypedValue.COMPLEX_UNIT_DIP,
		        200, 
		        r.getDisplayMetrics()));*/
		try {
			Elements elements = util.getByClass("http://www.arma3.com/news", "news_article");
			elements = util.removeMedia(elements);
			for (int i = 0; i < elements.size(); i++) {
				sContent = elements.get(i).html();
				wvEntry = new WebView(getActivity());
				//wvEntry.setLayoutParams(params);
				wvEntry.loadDataWithBaseURL(null, sContent, "text/html", "utf-8", null);
				llContent.addView(wvEntry);
				Log.e("FFF", elements.get(i).select("header").text());
			}
		} catch (IOException e) {
			Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}
}

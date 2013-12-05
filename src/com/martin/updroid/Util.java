package com.martin.updroid;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.content.Context;

public class Util {
	private Context context;

	public Util(Context context) {
		super();
		this.context = context;
	}
	
	public Elements getByClass(String url, String classname) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc.getElementsByClass(classname);
	}
	
	public Elements removeMedia(Elements elements) {
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).select("[src]").remove();
		}
		return elements;
	}
	
	public NewsCollection getA3_News() {
		
	}

}

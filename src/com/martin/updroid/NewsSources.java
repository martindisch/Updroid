package com.martin.updroid;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.content.Context;
import android.widget.Toast;

public class NewsSources {
	private Context context;

	public NewsSources(Context context) {
		super();
		this.context = context;
	}

	public NewsCollection getA3_News() {
		final String url = "http://www.arma3.com/news";
		final String classname = "news_article";

		Document doc = null;
		Elements content = null;
		
		String[] titles = { "null" };
		String[] urls = { "null" };
		String[] contents = { "null" };
		
		NewsCollection nColl = new NewsCollection(titles, urls, contents);

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			doc = null;
		}
		if (!(doc == null)) {
			content = doc.getElementsByClass(classname);

			titles = new String[content.size()];
			urls = new String[content.size()];
			contents = new String[content.size()];

			for (int i = 0; i < content.size(); i++) {
				content.get(i).select("[src]").remove();
				titles[i] = content.get(i).select("header").text();
				urls[i] = content.get(i).select("header").select("a[href]").attr("abs:href");
				contents[i] = content.get(i).getElementsByClass("post_content").text();
			}
			
			nColl = new NewsCollection(titles, urls, contents);
		}
		
		return nColl;
	}
	
	public NewsCollection getA3_Devhub() {
		final String url = "http://dev.arma3.com/";
		final String classname = "news_article";

		Document doc = null;
		Elements content = null;
		
		String[] titles = { "null" };
		String[] urls = { "null" };
		String[] contents = { "null" };
		
		NewsCollection nColl = new NewsCollection(titles, urls, contents);

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			doc = null;
		}
		if (!(doc == null)) {
			content = doc.getElementsByClass(classname);

			titles = new String[content.size()];
			urls = new String[content.size()];
			contents = new String[content.size()];

			for (int i = 0; i < content.size(); i++) {
				content.get(i).select("[src]").remove();
				titles[i] = content.get(i).select("header").text();
				urls[i] = content.get(i).select("header").select("a[href]").attr("abs:href");
				contents[i] = content.get(i).getElementsByClass("post_content").text();
			}
			
			nColl = new NewsCollection(titles, urls, contents);
		}
		
		return nColl;
	}
	
	public NewsCollection getSpaceEngineers_News() {
		final String url = "http://www.spaceengineersgame.com/news.html";
		final String classname = "paragraph";

		Document doc = null;
		Elements content = null;
		
		String[] titles = { "null" };
		String[] urls = { "null" };
		String[] contents = { "null" };
		
		NewsCollection nColl = new NewsCollection(titles, urls, contents);

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			doc = null;
		}
		if (!(doc == null)) {
			content = doc.getElementsByClass(classname);

			titles = new String[content.size()];
			urls = new String[content.size()];
			contents = new String[content.size()];

			for (int i = 0; i < content.size(); i++) {
				content.get(i).select("[src]").remove();
				titles[i] = content.get(i).select("strong").text();
				urls[i] = content.get(i).select("a[href]").attr("abs:href");
				content.get(i).select("strong").remove();
				content.get(i).select("font[size]").remove();
				contents[i] = content.get(i).text();
			}
			
			nColl = new NewsCollection(titles, urls, contents);
		}
		
		return nColl;
	}
	
	public NewsCollection getLayer_News() {
		final String url = "https://layer.com/news/1";
		final String classname = "post";

		Document doc = null;
		Elements content = null;
		
		String[] titles = { "null" };
		String[] urls = { "null" };
		String[] contents = { "null" };
		
		NewsCollection nColl = new NewsCollection(titles, urls, contents);

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			doc = null;
		}
		if (!(doc == null)) {
			content = doc.getElementsByClass(classname);

			titles = new String[content.size()];
			urls = new String[content.size()];
			contents = new String[content.size()];

			for (int i = 0; i < content.size(); i++) {
				content.get(i).select("[src]").remove();
				urls[i] = content.get(i).select("header").select("a[class]").select("a[href]").attr("abs:href");
				titles[i] = content.get(i).select("header").select("a[class]").text();
				content.get(i).select("header").select("a[class]").remove();
				contents[i] = content.get(i).select("header").select("a[href]").text();
			}
			
			nColl = new NewsCollection(titles, urls, contents);
		}
		
		return nColl;
	}

}

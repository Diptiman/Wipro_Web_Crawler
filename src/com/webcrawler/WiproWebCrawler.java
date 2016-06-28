package com.webcrawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Diptiman Adak
 *
 */
public class WiproWebCrawler {

	private static Logger LOGGER = Logger.getLogger(WiproWebCrawler.class.getName());
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WiproWebCrawler(String url) {
		this.url = url;
	}

	/**
	 * Main crawler function
	 */
	public void crawler() {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			crawlThroughImages(doc);
			crawlThroughUrls(doc);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}

	}

	/**
	 * Find the static images in a given doc
	 * 
	 * @param doc
	 */
	private void crawlThroughImages(Document doc) {
		Elements srcFiles = doc.select("[src]");
		int count = 0;
		System.out.println("========Image Resources=======");
		for (Element src : srcFiles) {
			if (src.tagName().equals("img")) {
				System.out.println("Image" + count + "::" + src.attr("abs:src"));
				count++;
			}
		}
	}

	/**
	 * Find the url in a given doc
	 * 
	 * @param doc
	 */
	private void crawlThroughUrls(Document doc) {
		Elements hrefs = doc.select("a");
		HashSet<String> links = new HashSet<String>();
		for (Element link : hrefs) {
			links.add(link.attr("href").trim());
		}
		int count = 0;
		System.out.println("========URL=======");
		for (String url : links) {
			System.out.println("Url" + count + "::" + url);
			count++;
		}

	}

}

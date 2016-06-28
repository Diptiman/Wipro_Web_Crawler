package com.webcrawler;

/**
 * 
 * @author Diptiman Adak
 *
 */
public class CrawlerMain {

	public static void main(String args[]) {
		WiproWebCrawler crawler = new WiproWebCrawler("http://wiprodigital.com/");
		crawler.crawler();
	}
}

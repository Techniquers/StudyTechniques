package com.yovee.test;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.FileCache;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyTestPageProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	private String startIndex = "list";
	private String regexExpress = "http://www\\.haoyun56\\.com/lineprice/\\d{1,8}.html";

	public static void main(String[] args) {
		String startURL = "http://www.haoyun56.com/lineprice/list_c200_p1.html";
		String path = "D:/webmagic";
		Spider.create(new MyTestPageProcessor()).addUrl(startURL).thread(1)
				.addPipeline(new FileCache(startURL, startURL, path)).run();
	}

	@Override
	public void process(Page page) {
		// href="http://www.haoyun56.com/lineprice/224216.html"
		page.toString();
		String pageURL = page.getUrl().get();
		// 起始页
		if (pageURL.indexOf(startIndex) >= 0) {
			page.getHtml().links();
			List<String> requests =  page.getHtml().regex(regexExpress).all();
			page.addTargetRequests(requests);
		} else if (pageURL.matches(regexExpress)) {
			String content = page.getHtml().xpath(
//					"//td[@style='border-bottom: #CCCCCC 1px solid; text-align:center;']/span"
					"//form[@id='form1']/div[4]/div[2]/table"
					
					
					).get();
			 page.getHtml().xpath("//table/text()");
		}

	}

	@Override
	public Site getSite() {
		return site;
	}

}

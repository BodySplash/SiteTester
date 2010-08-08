package fr.arpinum.siteTester.tools;

import java.util.Collection;

import com.torunski.crawler.Crawler;
import com.torunski.crawler.core.ICrawler;
import com.torunski.crawler.filter.ServerFilter;
import com.torunski.crawler.link.Link;
import com.torunski.crawler.model.MaxDepthModel;
import com.torunski.crawler.parser.httpclient.SimpleHttpClientParser;

import fr.arpinum.siteTester.domain.Site;

public class Spider {

	public Spider(final Site site) {
		this.site = site;
	}

	public Site getSite() {
		return site;
	}

	public void crawl() {
		ICrawler crawler = createCrawler();
		crawler.start();
		addResourcesToSite(crawler.getModel().getVisitedURIs());
	}

	private ICrawler createCrawler() {
		ICrawler crawler = new Crawler();
		crawler.setParser(new SimpleHttpClientParser());
		crawler.setModel(new MaxDepthModel(2));
		Site site = getSite();
		crawler.setLinkFilter(new ServerFilter(site.getUrl()));
		crawler.getModel().add(null, site.getUrl());
		return crawler;
	}

	private void addResourcesToSite(@SuppressWarnings("rawtypes") Collection visitedURIs) {
		Site stuff = getSite();
		for (Object link : visitedURIs) {
			Link resource = (Link) link;
			stuff.addResource(resource.getURI().replace(stuff.getUrl(), ""));
		}
	}

	private Site site;

}

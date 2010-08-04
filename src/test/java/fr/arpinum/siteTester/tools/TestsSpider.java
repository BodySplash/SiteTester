package fr.arpinum.siteTester.tools;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

import fr.arpinum.siteTester.domain.Resource;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.test.WithFakeSite;

public class TestsSpider {

	@Rule
	public WithFakeSite fakeSite = new WithFakeSite();

	@Test
	public void canCrawl() {
		Site site = new Site(fakeSite.rootUri());
		Spider spider = new Spider(site);

		spider.crawl();

		assertThat(site.getResources().isEmpty(), is(false));
		assertThat(site.getResources(), hasItem(new Resource(site, "/pages/index.htm")));
	}
}

package fr.arpinum.siteTester.tools;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.test.WithDatabase;

public class TestsSpiderExecutor {

	@Rule
	public WithDatabase database = new WithDatabase();

	@Test
	public void canRun() throws InterruptedException {
		SpiderExecutor executor = new SpiderExecutor(database.getInstance());
		Spider spider = mock(Spider.class);

		executor.schedule(spider);

		Thread.sleep(100);
		verify(spider).crawl();
	}

	@Test
	public void commitModifications() throws InterruptedException {
		SpiderExecutor executor = new SpiderExecutor(database.getInstance());
		Site site = new Site("http://toto");
		Repositories.sites().add(site);
		Spider spider = new MockSpider(site);

		executor.schedule(spider);
		Thread.sleep(100);
		database.flush();

		Site foundSite = Repositories.sites().getByName("toto");
		assertThat(foundSite.getResources().size(), is(1));
	}

	public class MockSpider extends Spider {

		public MockSpider(Site site) {
			super(site);
		}

		@Override
		public void crawl() {
			getSite().addResource("/test");
		}

	}

}

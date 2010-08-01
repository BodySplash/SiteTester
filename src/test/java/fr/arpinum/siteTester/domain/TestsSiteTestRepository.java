package fr.arpinum.siteTester.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import fr.arpinum.siteTester.test.WithDatabase;
import fr.arpinum.siteTester.test.WithFakeSite;
import fr.arpinum.siteTester.tools.Database;
import fr.arpinum.siteTester.tools.MockResourceCapturer;

public class TestsSiteTestRepository {

	@Rule
	public WithFakeSite fakeSite = new WithFakeSite();

	@Rule
	public WithDatabase database = new WithDatabase();

	@Test
	public void canPersist() {
		SiteTest siteTest = createSiteTest();
		SiteTestRepository repo = new SiteTestRepository();
		repo.add(siteTest);
		Database.INSTANCE.closeAndOpen();

		List<SiteTest> list = repo.getAll();
		assertThat(list.size(), is(1));
		SiteTest siteFound = list.get(0);
		assertThat(siteFound.captures().size(), is(1));
		assertThat(siteFound.captures().get(0).pngData(), is("un png".getBytes()));
	}

	private SiteTest createSiteTest() {
		Site site = new Site("http://localhost:9191");
		site.addResource("/index.htm");
		SiteTest siteTest = new SiteTest(site);
		siteTest.setResourceCapturer(new MockResourceCapturer());
		siteTest.run();
		return siteTest;
	}
}

package fr.arpinum.siteTester.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.arpinum.siteTester.tools.MockResourceCapturer;

public class TestsSiteTest {

	@Before
	public void before() {
		site = createSite();
		resourceCapturer = new MockResourceCapturer();
		tester = new SiteTest(site);
		tester.setResourceCapturer(resourceCapturer);
	}

	private Site createSite() {
		Site site = new Site("http://fantaisy");
		site.addResource("/accueil");
		site.addResource("/about");
		return site;
	}

	@Test
	public void canTakeScreenshots() {
		tester.run();

		assertThat(resourceCapturer.captureCallCout, is(2));
		assertThat(resourceCapturer.uris, hasItem("http://fantaisy/accueil"));
		assertThat(resourceCapturer.uris, hasItem("http://fantaisy/about"));
	}

	@Test
	public void testingKeepsCaptures() {
		tester.run();

		assertThat(tester.captures(), notNullValue());
		assertThat(tester.captures().size(), is(2));
		Capture capture = tester.captures().get(0);
		assertThat(capture.getPath(), is("/accueil"));
		assertThat(capture.pngData(), is("un png".getBytes()));
	}

	private Site site;
	private MockResourceCapturer resourceCapturer;
	private SiteTest tester;
}

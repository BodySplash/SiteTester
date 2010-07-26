package fr.arpinum.siteTester.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestsSite {

	private static final String TEST_SITE_URL = "http://www.tiron.fr";
	private Site site;

	@Before
	public void before() {
		site = new Site(TEST_SITE_URL);
	}

	@Test
	public void canCreateSite() {
		assertThat(site.getUrl(), is(TEST_SITE_URL));
	}

	@Test
	public void canAddResource() {
		Resource resource = site.addResource("/accueil.do");

		assertThat(resource, notNullValue());
		assertThat(resource.fullPath(), is(TEST_SITE_URL + "/accueil.do"));
		assertThat(resource.relativePath(), is("/accueil.do"));
		assertThat(site.resources(), hasItem(resource));
	}
}

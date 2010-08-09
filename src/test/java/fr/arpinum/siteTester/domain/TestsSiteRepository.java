package fr.arpinum.siteTester.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import fr.arpinum.siteTester.test.WithDatabase;

public class TestsSiteRepository {

	@Rule
	public WithDatabase database = new WithDatabase();

	@Before
	public void before() {
		repository = new SiteRepository(database.getInstance());
	}

	@Test
	public void canPersist() {
		Site site = new Site("http://somesite");
		site.addResource("/accueil.do");
		repository.add(site);
		database.flush();

		List<Site> all = repository.getAll();

		assertThat(all.size(), is(1));
		Site siteRetrived = all.get(0);
		assertThat(siteRetrived.getUrl(), is("http://somesite"));
		assertThat(siteRetrived.getResources(), hasItem(new Resource(siteRetrived, "/accueil.do")));
	}

	@Test
	public void canUpdate() {
		Site site = new Site("http://somesite");
		repository.add(site);
		site.addResource("/test");
		repository.update(site);
		database.flush();

		List<Site> all = repository.getAll();

		assertThat(all.size(), is(1));
		Site siteRetrived = all.get(0);
		assertThat(siteRetrived.getUrl(), is("http://somesite"));
		assertThat(siteRetrived.getResources(), hasItem(new Resource(siteRetrived, "/test")));

	}

	@Test
	public void canGetByName() {
		repository.add(new Site("http://somesite"));
		repository.add(new Site("http://arpinum.fr"));

		Site siteRetrieved = repository.getByName("somesite");

		assertThat(siteRetrieved, notNullValue());
		assertThat(siteRetrieved.getUrl(), is("http://somesite"));
	}

	private SiteRepository repository;
}

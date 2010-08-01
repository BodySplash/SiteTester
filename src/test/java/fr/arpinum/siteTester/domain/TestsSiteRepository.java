package fr.arpinum.siteTester.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import fr.arpinum.siteTester.test.WithDatabase;
import fr.arpinum.siteTester.tools.Database;

public class TestsSiteRepository {

	@Rule
	public WithDatabase database = new WithDatabase();

	@Test
	public void canPersist() {
		SiteRepository repository = new SiteRepository();
		Site site = new Site("http://somesite");
		site.addResource("/accueil.do");
		repository.add(site);
		Database.INSTANCE.closeAndOpen();

		List<Site> all = repository.getAll();

		assertThat(all.size(), is(1));
		Site siteRetrived = all.get(0);
		assertThat(siteRetrived.getUrl(), is("http://somesite"));
		assertThat(siteRetrived.resources(), hasItem(new Resource(siteRetrived, "/accueil.do")));
	}

}

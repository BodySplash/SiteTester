package fr.arpinum.siteTester.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Repository;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.test.WithWebServer;

public class TestsSitesResource {

	@Rule
	public WithWebServer server = new WithWebServer();

	@Before
	public void before() {
		clientResource = server.getRessource("/sites");
		repo = Repositories.sites();
	}

	@Test
	public void isMapped() {
		clientResource.get();

		assertThat(clientResource.getStatus(), is(Status.SUCCESS_OK));
	}

	@Test
	public void canRepresent() {
		Site site = new Site("http://pasloin");
		repo.add(site);
		SitesResource resource = new SitesResource();

		SiteTesterTemplateRepresentation representation = (SiteTesterTemplateRepresentation) resource.represent();

		assertThat(representation.getDataModel(), hasEntry(is("sites"), is(List.class)));
		List<Site> sites = representation.get("sites");
		assertThat(sites.size(), is(1));
		assertThat(sites, hasItem(site));
	}

	@Test
	public void canAdd() {
		Form form = new Form();
		form.add("url", "http://test");
		ClientResource resource = server.getRessource("/sites");

		resource.post(form);

		assertThat(repo.getAll().size(), is(1));
		assertThat(repo.getAll().get(0).getUrl(), is("http://test"));

	}

	private ClientResource clientResource;
	private Repository<Site> repo;
}

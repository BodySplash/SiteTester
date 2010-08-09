package fr.arpinum.siteTester.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.resource.ClientResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.test.WithWebServer;

public class TestsSiteTestsResource {

	@Rule
	public WithWebServer server = new WithWebServer();

	@Before
	public void before() {
		site = new Site("arpinum.fr");
		Repositories.sites().add(site);
		resource = server.getRessource("/sites/arpinum.fr/tests");
	}

	@Test
	public void canCreateTest() {
		resource.post(new EmptyRepresentation());

		assertThat(Repositories.siteTests().getAll().size(), is(1));
		assertThat(Repositories.siteTests().getAll().get(0).getSite(), is(site));
		assertThat(resource.getStatus(), is(Status.SUCCESS_CREATED));
	}

	private Site site;
	private ClientResource resource;
}

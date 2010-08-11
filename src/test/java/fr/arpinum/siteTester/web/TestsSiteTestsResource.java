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
import fr.arpinum.siteTester.test.TestResourceFactory;
import fr.arpinum.siteTester.test.WithWebServer;

public class TestsSiteTestsResource {

	@Rule
	public WithWebServer server = new WithWebServer();

	@Before
	public void before() {
		site = new Site("arpinum.fr");
		Repositories.sites().add(site);
	}

	@Test
	public void isMapped() {
		ClientResource resource = server.getRessource("/sites/arpinum.fr/tests");

		resource.post(new EmptyRepresentation());

		assertThat(resource.getStatus(), is(Status.SUCCESS_CREATED));
	}

	@Test
	public void canCreateTest() throws InstantiationException, IllegalAccessException {
		SiteTestsResource resource = new TestResourceFactory().withAttribute("uri", "arpinum.fr").newResource(
				SiteTestsResource.class);
		resource.testExecutor = new MockTestExecutor();

		resource.create();

		assertThat(Repositories.siteTests().getAll().size(), is(1));
		assertThat(Repositories.siteTests().getAll().get(0).getSite(), is(site));
		assertThat(resource.getStatus(), is(Status.SUCCESS_CREATED));
	}

	@Test
	public void creatingATestRunsIt() throws InstantiationException, IllegalAccessException {
		SiteTestsResource resource = new TestResourceFactory().withAttribute("uri", "arpinum.fr").newResource(
				SiteTestsResource.class);
		MockTestExecutor executor = new MockTestExecutor();
		resource.testExecutor = executor;

		resource.create();

		assertThat(executor.testScheduled, notNullValue());
		assertThat(executor.testScheduled.getSite(), is(site));
	}

	private Site site;
}

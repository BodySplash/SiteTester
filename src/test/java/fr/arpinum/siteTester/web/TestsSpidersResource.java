package fr.arpinum.siteTester.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Rule;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.resource.ClientResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.test.MockSpiderExecutor;
import fr.arpinum.siteTester.test.WithWebServer;

public class TestsSpidersResource {

	@Rule
	public WithWebServer server = new WithWebServer();

	@Test
	public void canCreateSpider() throws UnsupportedEncodingException {
		String url = "http://arpinum.fr";
		Site site = new Site(url);
		Repositories.sites().add(site);
		ClientResource resource = server.getRessource("/sites/arpinum.fr/spiders");

		resource.post(new EmptyRepresentation());

		assertThat(resource.getStatus(), is(Status.SUCCESS_ACCEPTED));
		assertThat(MockSpiderExecutor.lastSpider.getSite(), is(site));
	}

}

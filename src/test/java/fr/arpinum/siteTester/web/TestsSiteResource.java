package fr.arpinum.siteTester.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.test.WithWebServer;

public class TestsSiteResource {

	@Rule
	public WithWebServer server = new WithWebServer();

	@Test
	public void isMapped() throws IOException {
		final Site site = new Site("http://www.arpinum.fr");
		Repositories.sites().add(site);
		final ClientResource resource = server.getRessource("/sites/" + site.getName());

		final Representation representation = resource.get();

		assertThat(resource.getStatus(), is(Status.SUCCESS_OK));
		assertThat(representation.getText(), containsString("arpinum.fr"));
	}
}

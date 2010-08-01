package fr.arpinum.siteTester.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import fr.arpinum.siteTester.test.WithWebServer;

public class TestsWelcomeResource {

	@Rule
	public WithWebServer server = new WithWebServer();

	@Test
	public void isMapped() throws IOException {
		ClientResource resource = server.getRessource("/");

		Representation representation = resource.get();

		assertThat(resource.getStatus(), is(Status.SUCCESS_OK));
		assertThat(representation.getText(), containsString("sites"));
	}

}

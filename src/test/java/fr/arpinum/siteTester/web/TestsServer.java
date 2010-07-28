package fr.arpinum.siteTester.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TestsServer {

	@Test
	public void canStart() {
		Server server = new Server(7777);

		server.start();

		ClientResource resource = new ClientResource("http://localhost:7777");
		try {
			resource.get();
		} catch (ResourceException e) {
			assertThat(e.getStatus(), not(Status.CONNECTOR_ERROR_INTERNAL));
		}
	}
}

package fr.arpinum.siteTester.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TestsServer {

	@Before
	public void before() {
		server = new Server(7777);
	}

	@After
	public void after() {
		server.stop();
	}

	@Test
	public void canStart() {
		server.start();

		ClientResource resource = new ClientResource("http://localhost:7777");
		try {
			resource.get();
		} catch (ResourceException e) {
			assertThat(e.getStatus(), not(Status.CONNECTOR_ERROR_INTERNAL));
		}
	}

	private Server server;
}

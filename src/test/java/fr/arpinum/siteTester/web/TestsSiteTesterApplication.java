package fr.arpinum.siteTester.web;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import fr.arpinum.siteTester.test.WithWebServer;

public class TestsSiteTesterApplication {

	@Rule
	public WithWebServer server = new WithWebServer();

	@Test
	public void canServeCssFile() {
		ClientResource resource = server.getRessource("/theme/default.css");

		try {
			resource.get();
		} catch (ResourceException e) {
			fail();
		}
	}

}

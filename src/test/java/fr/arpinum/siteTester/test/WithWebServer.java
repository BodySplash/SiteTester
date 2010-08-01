package fr.arpinum.siteTester.test;

import org.junit.rules.ExternalResource;
import org.restlet.resource.ClientResource;

import fr.arpinum.siteTester.web.Server;

public class WithWebServer extends ExternalResource {

	protected void before() throws Throwable {
		server.start();
	};

	public ClientResource getRessource(String relativeUri) {
		return new ClientResource(String.format("http://localhost:%s%s", PORT, relativeUri));
	}

	private static final int PORT = 8888;
	private static final Server server = new Server(PORT);

}

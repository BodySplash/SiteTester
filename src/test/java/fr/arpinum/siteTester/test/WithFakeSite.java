package fr.arpinum.siteTester.test;

import org.junit.rules.ExternalResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class WithFakeSite extends ExternalResource {

	public WithFakeSite() {
		initializeOnce();
	}

	private void initializeOnce() {
		if (component == null) {
			component = new Component();
			component.getServers().add(Protocol.HTTP, 9191);
			component.getClients().add(Protocol.CLAP);
			component.getDefaultHost().attach(new FakeSite());
		}
	}

	@Override
	protected void before() throws Throwable {
		if (component.isStopped()) {
			component.start();
		}
	}

	public String rootUri() {
		return "http://localhost:9191";
	}

	private class FakeSite extends Application {
		@Override
		public Restlet createInboundRoot() {
			Router routeur = new Router();
			routeur.attach("/pages", new Directory(getContext(), new Reference(Protocol.CLAP, "class/site")));
			routeur.attachDefault(DefaultResource.class);
			return routeur;
		}
	}

	private static Component component;

}

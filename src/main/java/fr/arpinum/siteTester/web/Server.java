package fr.arpinum.siteTester.web;

import org.apache.log4j.Logger;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class Server {

	public static void main(String[] args) {
		int port = 8080;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		SiteTesterModule module = new SiteTesterModule(port);
		Injector injector = Guice.createInjector(module);
		injector.getInstance(Server.class).start();
	}

	@Inject
	public Server(Application application, @Named("PORT") int port) {
		component = new Component();
		component.getServers().add(Protocol.HTTP, port);
		component.getClients().add(Protocol.CLAP);
		component.getDefaultHost().attach(application);
	}

	public void start() {
		if (component.isStopped()) {
			try {
				component.start();
			} catch (Exception e) {
				LOGGER.error("Error while starting the server", e);
			}
		}
	}

	public void stop() {
		try {
			component.stop();
		} catch (Exception e) {
			LOGGER.error("Can't stop server", e);
		}
	}

	private static final Logger LOGGER = Logger.getLogger(Server.class);
	private Component component;
}

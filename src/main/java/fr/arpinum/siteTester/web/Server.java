package fr.arpinum.siteTester.web;

import org.apache.log4j.Logger;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class Server {

	public static void main(String[] args) {
		int port = 8080;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		new Server(port).start();
	}

	public Server(int port) {
		component = new Component();
		component.getServers().add(Protocol.HTTP, port);
		component.getClients().add(Protocol.CLAP);
		component.getDefaultHost().attach(new SiteTesterApplication());
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

	private static final Logger LOGGER = Logger.getLogger(Server.class);
	private Component component;
}

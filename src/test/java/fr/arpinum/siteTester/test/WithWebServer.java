package fr.arpinum.siteTester.test;

import java.io.File;

import org.junit.rules.ExternalResource;
import org.restlet.resource.ClientResource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.arpinum.siteTester.tools.Database;
import fr.arpinum.siteTester.tools.SpiderExecutor;
import fr.arpinum.siteTester.web.Server;

public class WithWebServer extends ExternalResource {

	@Override
	protected void before() throws Throwable {
		initializeOnce();
		server.start();
	};

	@Override
	protected void after() {
		Database.INSTANCE.close();
		File file = new File("dbtest.db4o");
		file.delete();
		Database.INSTANCE.open(file);
	}

	private synchronized static void initializeOnce() {
		if (server == null) {
			injector = Guice.createInjector(new TestSiteTesterModule(PORT));
			server = injector.getInstance(Server.class);
		}
	}

	public SpiderExecutor getSpiderExecutor() {
		return injector.getInstance(SpiderExecutor.class);
	}

	public ClientResource getRessource(String relativeUri) {
		return new ClientResource(String.format("http://localhost:%s%s", PORT, relativeUri));
	}

	private static final int PORT = 8888;
	private static Server server;
	private static Injector injector;

}

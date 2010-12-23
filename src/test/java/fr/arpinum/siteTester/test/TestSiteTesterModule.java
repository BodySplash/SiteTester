package fr.arpinum.siteTester.test;

import org.restlet.Application;

import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fr.arpinum.siteTester.tools.Database;
import fr.arpinum.siteTester.tools.SpiderExecutor;
import fr.arpinum.siteTester.tools.TestExecutor;
import fr.arpinum.siteTester.web.MockTestExecutor;
import fr.arpinum.siteTester.web.SiteTesterApplication;
import fr.arpinum.siteTester.web.SiteTesterModule;

public class TestSiteTesterModule extends SiteTesterModule {

	public TestSiteTesterModule(int port) {
		super(port);
	}

	@Override
	protected void configure() {
		bind(Application.class).to(SiteTesterApplication.class);
		bind(SpiderExecutor.class).to(MockSpiderExecutor.class).in(Singleton.class);
		bind(TestExecutor.class).to(MockTestExecutor.class);
		bind(Integer.class).annotatedWith(Names.named("PORT")).toInstance(getPort());
		bind(Database.class).toInstance(createDatabase("dbtest.db4o"));
	}
}

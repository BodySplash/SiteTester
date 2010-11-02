package fr.arpinum.siteTester.web;

import java.io.File;

import org.restlet.Application;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fr.arpinum.siteTester.tools.Database;
import fr.arpinum.siteTester.tools.FirefoxResourceCapturer;
import fr.arpinum.siteTester.tools.ResourceCapturer;
import fr.arpinum.siteTester.tools.SpiderExecutor;
import freemarker.template.Configuration;

public class SiteTesterModule extends AbstractModule {

	public SiteTesterModule(int port) {
		this.port = port;
	}

	@Override
	protected void configure() {
		bind(Application.class).to(SiteTesterApplication.class);
		bind(SpiderExecutor.class).in(Singleton.class);
		bind(Integer.class).annotatedWith(Names.named("PORT")).toInstance(getPort());
		bind(Database.class).toInstance(createDatabase("db.db4o"));
		bind(ResourceCapturer.class).to(FirefoxResourceCapturer.class);
	}

	protected Class<? extends SpiderExecutor> getSpiderExecutorClass() {
		return SpiderExecutor.class;
	}

	@Provides
	public Configuration providesConfiguration() {
		Configuration freemarkerConfiguration = new Configuration();
		freemarkerConfiguration.setDefaultEncoding("UTF-8");
		freemarkerConfiguration.setClassForTemplateLoading(getClass(), "/templates");
		return freemarkerConfiguration;
	}

	protected Database createDatabase(String dbFile) {
		return Database.open(new File(dbFile));
	}

	public int getPort() {
		return port;
	}

	private final int port;

}

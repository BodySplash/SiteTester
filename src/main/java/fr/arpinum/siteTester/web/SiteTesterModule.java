package fr.arpinum.siteTester.web;

import org.restlet.Application;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

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
		bind(Integer.class).annotatedWith(Names.named("PORT")).toInstance(port);
		bind(String.class).annotatedWith(Names.named("DbFile")).toInstance(getDatabaseFilePath());
	}

	@Provides
	public Configuration providesConfiguration() {
		Configuration freemarkerConfiguration = new Configuration();
		freemarkerConfiguration.setDefaultEncoding("UTF-8");
		freemarkerConfiguration.setClassForTemplateLoading(getClass(), "/templates");
		return freemarkerConfiguration;
	}

	protected String getDatabaseFilePath() {
		return "db.db4o";
	}

	private final int port;
}

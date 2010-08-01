package fr.arpinum.siteTester.web;

import java.io.File;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Filter;

import fr.arpinum.siteTester.tools.Database;
import freemarker.template.Configuration;

public class SiteTesterApplication extends Application {

	public SiteTesterApplication() {
		configureFreemarker();
	}

	private void configureFreemarker() {
		freemarkerConfiguration = new Configuration();
		freemarkerConfiguration.setDefaultEncoding("UTF-8");
		freemarkerConfiguration.setClassForTemplateLoading(getClass(), "/templates");
	}

	public static SiteTesterApplication getInstance() {
		return (SiteTesterApplication) Application.getCurrent();
	}

	@Override
	public synchronized void start() throws Exception {
		super.start();
		Database.INSTANCE.open(new File("db.db4o"));
	}

	@Override
	public synchronized void stop() throws Exception {
		super.stop();
		Database.INSTANCE.close();
	}

	@Override
	public Restlet createInboundRoot() {
		Filter filter = new OpenSessionInViewFilter();
		filter.setNext(new SiteTesterRouter(getContext()));
		return filter;
	}

	public Configuration getConfiguration() {
		return freemarkerConfiguration;
	}

	private Configuration freemarkerConfiguration;
}

package fr.arpinum.siteTester.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Filter;

import com.google.inject.Inject;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.tools.Database;
import freemarker.template.Configuration;

public class SiteTesterApplication extends Application {

	public static SiteTesterApplication getInstance() {
		return (SiteTesterApplication) Application.getCurrent();
	}

	@Override
	public synchronized void start() throws Exception {
		super.start();
		Repositories.setDatabase(database);
	}

	@Override
	public synchronized void stop() throws Exception {
		super.stop();
		database.close();
	}

	@Override
	public Restlet createInboundRoot() {
		Filter filter = new OpenSessionInViewFilter(database);
		filter.setNext(new SiteTesterRouter(getContext(), finderFactory));
		return filter;
	}

	public Configuration getConfiguration() {
		return freemarkerConfiguration;
	}

	@Inject
	private Configuration freemarkerConfiguration;
	@Inject
	private Database database;
	@Inject
	private FinderFactory finderFactory;

}

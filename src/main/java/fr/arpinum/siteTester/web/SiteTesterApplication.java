package fr.arpinum.siteTester.web;

import java.io.File;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Filter;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fr.arpinum.siteTester.tools.Database;
import fr.arpinum.siteTester.tools.SpiderExecutor;
import freemarker.template.Configuration;

public class SiteTesterApplication extends Application {

	public static SiteTesterApplication getInstance() {
		return (SiteTesterApplication) Application.getCurrent();
	}

	@Inject
	public SiteTesterApplication(@Named("DbFile") String dbFilePath) {
		this.dbFile = new File(dbFilePath);
	}

	@Override
	public synchronized void start() throws Exception {
		super.start();
		Database.INSTANCE.open(dbFile);
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

	public SpiderExecutor getSpiderExecutor() {
		return spiderExecutor;
	}

	private File dbFile;

	@Inject
	private Configuration freemarkerConfiguration;
	@Inject
	private SpiderExecutor spiderExecutor;

}

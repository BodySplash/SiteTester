package fr.arpinum.siteTester.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import freemarker.template.Configuration;

public class SiteTesterApplication extends Application {

	public static SiteTesterApplication getInstance() {
		return (SiteTesterApplication) Application.getCurrent();
	}

	@Override
	public Restlet createInboundRoot() {
		Router result = new Router(getContext());
		result.attach("/", WelcomeResource.class);
		result.attach("/theme", new Directory(getContext(), new Reference("clap://class/static-content/theme")));
		result.attach("/js", new Directory(getContext(), new Reference("clap://class/static-content/js")));
		return result;
	}

	@Override
	public synchronized void start() throws Exception {
		freemarkerConfiguration = new Configuration();
		freemarkerConfiguration.setDefaultEncoding("UTF-8");
		freemarkerConfiguration.setClassForTemplateLoading(getClass(), "/templates");
		super.start();
	}

	public Configuration getConfiguration() {
		return freemarkerConfiguration;
	}

	private Configuration freemarkerConfiguration;
}

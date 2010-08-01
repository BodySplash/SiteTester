package fr.arpinum.siteTester.web;

import org.restlet.Application;
import org.restlet.Restlet;

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
	public Restlet createInboundRoot() {
		return new SiteTesterRouter(getContext());
	}

	public Configuration getConfiguration() {
		return freemarkerConfiguration;
	}

	private Configuration freemarkerConfiguration;
}

package fr.arpinum.siteTester.web;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class SiteTesterModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Integer.class).annotatedWith(Names.named("SERVER_PORT")).toInstance(7777);
	}

}

package fr.arpinum.siteTester.web;

import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class FinderFactory {

	@Inject
	public FinderFactory(Injector injector) {
		this.injector = injector;

	}

	public Finder finderOf(Class<? extends ServerResource> type) {
		return new ResourceFinder(type, injector);
	}

	private final Injector injector;
}

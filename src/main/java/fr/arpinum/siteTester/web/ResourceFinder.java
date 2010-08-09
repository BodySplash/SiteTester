package fr.arpinum.siteTester.web;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;

import com.google.inject.Injector;

public class ResourceFinder extends Finder {

	public ResourceFinder(Class<? extends ServerResource> type, Injector injector) {
		this.type = type;
		this.injector = injector;
	}

	@Override
	public Class<?> getTargetClass() {
		return type;
	}

	@Override
	public ServerResource create(Request request, Response response) {
		ServerResource instance = injector.getInstance(type);
		instance.setRequest(request);
		instance.setResponse(response);
		return instance;
	}

	private final Class<? extends ServerResource> type;
	private final Injector injector;
}

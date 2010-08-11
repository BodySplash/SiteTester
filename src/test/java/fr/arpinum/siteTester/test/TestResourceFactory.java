package fr.arpinum.siteTester.test;

import java.util.Map;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.resource.ServerResource;

import com.google.common.collect.Maps;

public class TestResourceFactory {

	public TestResourceFactory withAttribute(String key, String value) {
		attributes.put(key, value);
		return this;
	}

	public <T extends ServerResource> T newResource(Class<T> resourceType) throws InstantiationException,
			IllegalAccessException {
		Request request = new Request();
		request.getAttributes().putAll(attributes);
		Response response = new Response(request);
		T resource = resourceType.newInstance();
		resource.init(null, request, response);
		return resource;

	}

	private Map<String, String> attributes = Maps.newHashMap();

}

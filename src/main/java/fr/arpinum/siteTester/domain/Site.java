package fr.arpinum.siteTester.domain;

import java.util.List;

import com.google.common.collect.Lists;

public class Site {

	public Site(String url) {
		this.name = url.replace("http://", "");
	}

	public Resource addResource(String relativePath) {
		Resource newResource = new Resource(this, relativePath);
		resources.add(newResource);
		return newResource;
	}

	protected String getFullPath(Resource page) {
		return getUrl() + page.relativePath();
	}

	public List<Resource> getResources() {
		return resources;
	}

	public String getUrl() {
		return "http://" + name;
	}

	public String getName() {
		return name;
	}

	private List<Resource> resources = Lists.newArrayList();
	private final String name;
}

package fr.arpinum.siteTester.web;

import org.restlet.data.Status;
import org.restlet.resource.Post;

import com.google.inject.Inject;

import fr.arpinum.siteTester.tools.Spider;
import fr.arpinum.siteTester.tools.SpiderExecutor;

public class SpidersResource extends WithSiteResource {

	@Inject
	public SpidersResource(SpiderExecutor executor) {
		this.executor = executor;

	}

	@Post
	public void create() {
		executor.schedule(new Spider(getSite()));
		setStatus(Status.SUCCESS_ACCEPTED);
	}

	private final SpiderExecutor executor;

}

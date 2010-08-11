package fr.arpinum.siteTester.web;

import org.restlet.data.Status;
import org.restlet.resource.Post;

import com.google.inject.Inject;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.SiteTest;
import fr.arpinum.siteTester.tools.TestExecutor;

public class SiteTestsResource extends WithSiteResource {

	@Post
	public void create() {
		SiteTest test = new SiteTest(getSite());
		Repositories.siteTests().add(test);
		testExecutor.schedule(test);
		setStatus(Status.SUCCESS_CREATED);
	}

	@Inject
	public TestExecutor testExecutor;
}

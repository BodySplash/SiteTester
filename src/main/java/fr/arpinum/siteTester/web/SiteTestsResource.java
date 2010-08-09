package fr.arpinum.siteTester.web;

import org.restlet.data.Status;
import org.restlet.resource.Post;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.SiteTest;

public class SiteTestsResource extends WithSiteResource {

	@Post
	public void create() {
		SiteTest test = new SiteTest(getSite());
		Repositories.siteTests().add(test);
		setStatus(Status.SUCCESS_CREATED);
	}
}

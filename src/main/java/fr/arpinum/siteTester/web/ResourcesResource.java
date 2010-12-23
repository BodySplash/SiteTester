package fr.arpinum.siteTester.web;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class ResourcesResource extends WithSiteResource {

	@Get
	public Representation represent() {
		return SiteTesterTemplateRepresentation.forPath("/resources.ftl").with("resources",
				getSite().getResources());
	}
}

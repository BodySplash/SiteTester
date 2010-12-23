package fr.arpinum.siteTester.web;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class SiteResource extends WithSiteResource {

	@Get
	public Representation represent() {
		return SiteTesterTemplateRepresentation.forPath("/site.ftl").with("site", getSite());
	}
}

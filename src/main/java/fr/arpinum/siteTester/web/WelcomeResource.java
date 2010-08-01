package fr.arpinum.siteTester.web;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class WelcomeResource extends ServerResource {

	@Get
	public Representation represent() {
		return SiteTesterTemplateRepresentation.forPath("welcome.ftl");
	}

}

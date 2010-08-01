package fr.arpinum.siteTester.web;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.domain.SiteRepository;

public class SitesResource extends ServerResource {

	@Get
	public Representation represent() {
		SiteRepository repo = new SiteRepository();
		return SiteTesterTemplateRepresentation.forPath("/sites.ftl").with("sites", repo.getAll());
	}

	@Post
	public void add(Form form) {
		Site newSite = new Site(form.getFirstValue("url"));
		SiteRepository repo = new SiteRepository();
		repo.add(newSite);
	}
}

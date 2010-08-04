package fr.arpinum.siteTester.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.tools.Spider;

public class SpidersResource extends ServerResource {

	@Override
	protected void doInit() throws ResourceException {
		try {
			site = Repositories.sites().getByName(
					URLDecoder.decode(getRequestAttributes().get("uri").toString(), "UTF8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Post
	public void create() {
		SiteTesterApplication.getInstance().getSpiderExecutor().schedule(new Spider(site));
		setStatus(Status.SUCCESS_ACCEPTED);
	}

	private Site site;
}

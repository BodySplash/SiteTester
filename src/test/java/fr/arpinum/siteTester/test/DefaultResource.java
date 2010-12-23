package fr.arpinum.siteTester.test;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DefaultResource extends ServerResource {

	@Get
	public void représente() {
		setLocationRef("/pages/index.htm");
		setStatus(Status.REDIRECTION_PERMANENT);
	}
}

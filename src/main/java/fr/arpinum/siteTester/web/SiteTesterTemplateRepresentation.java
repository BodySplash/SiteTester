package fr.arpinum.siteTester.web;

import java.io.IOException;
import java.io.OutputStream;

import org.restlet.data.CharacterSet;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.OutputRepresentation;

public class SiteTesterTemplateRepresentation extends OutputRepresentation {

	public SiteTesterTemplateRepresentation(final String templateName) {
		super(MediaType.TEXT_HTML);
		setCharacterSet(CharacterSet.UTF_8);
		representation = new TemplateRepresentation(templateName, SiteTesterApplication.getInstance()
				.getConfiguration(), MediaType.TEXT_HTML);
	}

	@Override
	public void write(OutputStream outputStream) throws IOException {
		representation.write(outputStream);

	}

	private TemplateRepresentation representation;
}

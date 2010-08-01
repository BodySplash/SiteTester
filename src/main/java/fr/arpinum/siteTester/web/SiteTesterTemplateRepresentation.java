package fr.arpinum.siteTester.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.restlet.data.CharacterSet;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.OutputRepresentation;

import com.google.common.collect.Maps;

public class SiteTesterTemplateRepresentation extends OutputRepresentation {

	public static SiteTesterTemplateRepresentation forPath(String template) {
		return new SiteTesterTemplateRepresentation(template);
	}

	public SiteTesterTemplateRepresentation with(String key, Object value) {
		data.put(key, value);
		return this;
	}

	public SiteTesterTemplateRepresentation(final String templateName) {
		super(MediaType.TEXT_HTML);
		setCharacterSet(CharacterSet.UTF_8);
		representation = new TemplateRepresentation(templateName, SiteTesterApplication.getInstance()
				.getConfiguration(), MediaType.TEXT_HTML);
	}

	public SiteTesterTemplateRepresentation(String template, Map<String, Object> data) {
		this(template);
		this.data = data;
	}

	@Override
	public void write(OutputStream outputStream) throws IOException {
		representation.write(outputStream);

	}

	public Map<String, Object> getDataModel() {
		return data;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) data.get(key);
	}

	private TemplateRepresentation representation;
	private Map<String, Object> data = Maps.newHashMap();
}

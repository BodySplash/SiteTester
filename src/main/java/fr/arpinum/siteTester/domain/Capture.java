package fr.arpinum.siteTester.domain;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Capture {

	public Capture(final String path, final byte[] data) {
		this.path = path;
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public byte[] pngData() {
		return data;
	}

	public void exportToFile(File file) {
		try {
			FileUtils.writeByteArrayToFile(file, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private final byte[] data;
	private final String path;
}

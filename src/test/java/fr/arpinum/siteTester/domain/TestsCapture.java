package fr.arpinum.siteTester.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestsCapture {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void canExportToFile() throws IOException {
		Capture capture = new Capture("plop", "des données".getBytes());
		File file = tempFolder.newFile("test.png");

		capture.exportToFile(file);

		assertThat(FileUtils.readFileToString(file), is("des données"));
	}

}

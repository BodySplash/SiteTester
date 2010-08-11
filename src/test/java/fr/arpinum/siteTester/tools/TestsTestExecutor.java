package fr.arpinum.siteTester.tools;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.SiteTest;
import fr.arpinum.siteTester.test.WithDatabase;

public class TestsTestExecutor {

	@Rule
	public WithDatabase database = new WithDatabase();

	@Test
	public void canRun() throws InterruptedException {
		TestExecutor executor = new TestExecutor();
		SiteTest siteTest = mock(SiteTest.class);

		executor.schedule(siteTest);
		Thread.sleep(100);

		verify(siteTest).run();
	}

	@Test
	public void commitModifications() throws InterruptedException {
		TestExecutor executor = new TestExecutor();
		SiteTest siteTest = mock(SiteTest.class);

		executor.schedule(siteTest);
		Thread.sleep(100);
		database.flush();

		assertThat(Repositories.siteTests().getAll().size(), is(1));
	}
}

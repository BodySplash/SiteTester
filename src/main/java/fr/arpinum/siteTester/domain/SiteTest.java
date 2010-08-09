package fr.arpinum.siteTester.domain;

import java.util.List;

import com.google.common.collect.Lists;

import fr.arpinum.siteTester.tools.ResourceCapturer;

public class SiteTest {

	public SiteTest(final Site site) {
		this.site = site;
	}

	public Site getSite() {
		return site;
	}

	public void run() {
		for (Resource resource : site.getResources()) {
			captures.add(new Capture(resource.relativePath(), resourceCapturer.capture(resource)));
		}

	}

	public List<Capture> captures() {
		return captures;
	}

	public void setResourceCapturer(ResourceCapturer capturer) {
		resourceCapturer = capturer;
	}

	private List<Capture> captures = Lists.newArrayList();
	private final Site site;
	private ResourceCapturer resourceCapturer;
}

package fr.arpinum.siteTester.tools;

import java.util.List;

import com.google.common.collect.Lists;

import fr.arpinum.siteTester.domain.Resource;

public class MockResourceCapturer implements ResourceCapturer {

	public int captureCallCout;
	public List<String> uris = Lists.newArrayList();

	@Override
	public byte[] capture(Resource resource) {
		captureCallCout++;
		uris.add(resource.fullPath());
		return "un png".getBytes();
	}

}

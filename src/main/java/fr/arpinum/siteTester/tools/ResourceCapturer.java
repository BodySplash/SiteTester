package fr.arpinum.siteTester.tools;

import fr.arpinum.siteTester.domain.Resource;

public interface ResourceCapturer {

	byte[] capture(Resource resource);

}

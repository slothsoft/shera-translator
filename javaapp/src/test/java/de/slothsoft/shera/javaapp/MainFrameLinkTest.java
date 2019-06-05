package de.slothsoft.shera.javaapp;

import org.junit.Assert;
import org.junit.Test;

public class MainFrameLinkTest {

	@Test
	public void testDisplayName() throws Exception {
		for (final MainFrame.Link link : MainFrame.Link.values()) {
			final String stringToTest = link.getDisplayName();
			Assert.assertNotNull(stringToTest);
			Assert.assertFalse(stringToTest.startsWith("!"));
		}
	}
	@Test
	public void testUrl() throws Exception {
		for (final MainFrame.Link link : MainFrame.Link.values()) {
			final String stringToTest = link.getUrl();
			Assert.assertNotNull(stringToTest);
			Assert.assertFalse(stringToTest.startsWith("!"));
		}
	}
}

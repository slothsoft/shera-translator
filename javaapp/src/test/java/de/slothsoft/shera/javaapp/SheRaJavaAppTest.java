package de.slothsoft.shera.javaapp;

import org.junit.Assert;
import org.junit.Test;

public class SheRaJavaAppTest {

	@Test
	public void testImages() throws Exception {
		for (final String imagePath : SheRaJavaApp.ALL_IMAGES) {
			Assert.assertNotNull(SheRaJavaApp.fetchImage(imagePath));
		}
	}
}

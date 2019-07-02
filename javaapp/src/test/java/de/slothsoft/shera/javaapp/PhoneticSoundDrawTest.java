package de.slothsoft.shera.javaapp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.slothsoft.shera.PhoneticSound;

/**
 * This test should be closer to shera-core since it tests that I don't accidentally
 * change the drawing algorithm. Yet I test it against the implementation detail of the
 * {@link SwingCanvas}, that's why it's here.
 */

@RunWith(Parameterized.class)
public class PhoneticSoundDrawTest {

	private static final int IMAGE_SIZE = 100;
	private static final int IMAGE_PADDING = 5;
	private static final String IMAGE_FORMAT = "PNG";

	private static File tempDirectory;

	@Parameters(name = "{0}")
	public static Collection<Object[]> data() {
		final List<Object[]> result = new ArrayList<>();
		for (final PhoneticSound sound : PhoneticSound.values()) {
			result.add(new Object[]{sound});
		}
		return result;
	}

	private synchronized static File getTempDirectory() {
		if (tempDirectory == null) {
			tempDirectory = new File("target/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			Assert.assertTrue(tempDirectory.mkdirs());
			System.out.println("Prepared " + tempDirectory);
		}
		return tempDirectory;
	}

	private final PhoneticSound sound;

	public PhoneticSoundDrawTest(PhoneticSound sound) {
		this.sound = sound;
	}

	@Test
	public void testDrawOn() throws Exception {
		final BufferedImage actualImage = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D graphics = actualImage.createGraphics();
		try {
			PhoneticSoundImage.paintSoundOnGraphics(graphics, this.sound, IMAGE_SIZE, IMAGE_PADDING);
		} finally {
			graphics.dispose();
		}

		final BufferedImage expectedImage;
		final String fileName = "letters/" + this.sound + "." + IMAGE_FORMAT.toLowerCase();
		try (InputStream input = getClass().getResourceAsStream(fileName)) {
			Assert.assertNotNull("Could not find image " + fileName, input);
			expectedImage = ImageIO.read(input);
		}

		assertImagesEqual(expectedImage, actualImage);

	}

	private void assertImagesEqual(BufferedImage expectedImage, BufferedImage actualImage) throws IOException {
		try {
			doAssertImagesEqual(expectedImage, actualImage);
		} catch (final AssertionError e) {
			ImageIO.write(expectedImage, IMAGE_FORMAT,
					new File(getTempDirectory(), this.sound + "-expected." + IMAGE_FORMAT));
			ImageIO.write(actualImage, IMAGE_FORMAT,
					new File(getTempDirectory(), this.sound + "-actual." + IMAGE_FORMAT));
			throw e;
		}
	}

	private static void doAssertImagesEqual(BufferedImage expectedImage, BufferedImage actualImage) {
		Assert.assertEquals(expectedImage.getWidth(), actualImage.getWidth());
		Assert.assertEquals(expectedImage.getHeight(), actualImage.getHeight());

		final int width = expectedImage.getWidth();
		final int height = expectedImage.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Assert.assertEquals("Pixel on " + x + "|" + y + " does not match:",
						new Color(expectedImage.getRGB(x, y)), new Color(actualImage.getRGB(x, y)));
			}
		}
	}

}

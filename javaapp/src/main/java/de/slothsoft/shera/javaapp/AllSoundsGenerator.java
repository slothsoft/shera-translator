package de.slothsoft.shera.javaapp;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.batik.dom.GenericDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.slothsoft.shera.PhoneticSound;

public class AllSoundsGenerator {

	private static final int IMAGE_SIZE = 50;
	private static final int TEXT_SIZE = 20;
	private static final int IMAGE_PADDING = 5;
	private static final int SOUNDS_PER_LINE = 8;
	private static final File TARGET_FILE = new File("readme/all-sounds.png");

	public static void main(String[] args) throws IOException {
		if (!TARGET_FILE.getParentFile().exists()) {
			TARGET_FILE.getParentFile().mkdirs();
		}

		final DOMImplementation dom = GenericDOMImplementation.getDOMImplementation();
		final Document document = dom.createDocument("http://www.w3.org/2000/svg", "svg", null);

		// Create and init SVG graphics
		final int graphicsWidth = SOUNDS_PER_LINE * IMAGE_SIZE;
		final int graphicsHeight = (IMAGE_SIZE + TEXT_SIZE) * (PhoneticSound.values().length / SOUNDS_PER_LINE + 1);

		final BufferedImage image = new BufferedImage(graphicsWidth, graphicsHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D graphics = image.createGraphics();
		graphics.setColor(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		graphics.fillRect(0, 0, graphicsWidth, graphicsHeight);
		final FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
		int index = 0;

		for (final PhoneticSound sound : PhoneticSound.values()) {
			final int translateX = IMAGE_SIZE * (index % SOUNDS_PER_LINE);
			final int translateY = (IMAGE_SIZE + TEXT_SIZE) * (index / SOUNDS_PER_LINE);
			graphics.translate(translateX, translateY);

			PhoneticSoundImage.paintSoundOnGraphics(graphics, sound, IMAGE_SIZE, IMAGE_PADDING);

			// paint text
			final String soundName = sound.getDisplayName();
			final int textX = (IMAGE_SIZE - metrics.stringWidth(soundName)) / 2;
			final int textY = IMAGE_SIZE + ((TEXT_SIZE - metrics.getHeight()) / 2) + metrics.getAscent();
			graphics.drawString(soundName, textX, textY);

			graphics.translate(-translateX, -translateY);
			index++;
		}

		// Finally, stream out SVG to a file
		ImageIO.write(image, "PNG", TARGET_FILE);
		System.out.println("Generated PNG " + TARGET_FILE.getAbsolutePath());
	}
}
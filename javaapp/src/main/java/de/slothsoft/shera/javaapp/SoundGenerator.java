package de.slothsoft.shera.javaapp;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.slothsoft.shera.PhoneticSound;

public class SoundGenerator {

	private static final int IMAGE_SIZE = 100;
	private static final int IMAGE_PADDING = 10;
	private static final File TARGET_FOLDER = new File("webapp/src/main/webapp/letters");

	public static void main(String[] args) throws IOException {
		if (TARGET_FOLDER.exists()) {
			TARGET_FOLDER.delete();
		}
		TARGET_FOLDER.mkdirs();

		final DOMImplementation dom = GenericDOMImplementation.getDOMImplementation();
		final Document document = dom.createDocument("http://www.w3.org/2000/svg", "svg", null);

		for (final PhoneticSound sound : PhoneticSound.values()) {
			// Create SVG graphics and initialize canvas
			final SVGGraphics2D graphics = new SVGGraphics2D(document);
			graphics.setSVGCanvasSize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));
			PhoneticSoundImage.paintSoundOnGraphics(graphics, sound, IMAGE_SIZE, IMAGE_PADDING);

			// Finally, stream out SVG to a file
			try (FileOutputStream out = new FileOutputStream(
					new File(TARGET_FOLDER, sound.name().toLowerCase() + ".svg"));
					Writer outputStreamWriter = new OutputStreamWriter(out, "UTF-8")) {
				graphics.stream(outputStreamWriter);
			}
		}

		System.out.println("Generated SVG in " + TARGET_FOLDER.getAbsolutePath());
	}
}
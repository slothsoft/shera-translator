package de.slothsoft.shera;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * This class reads an image and retrieves all necessary information.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

class PhoneticSoundReader {

	private final static Color INPUT = new Color(255, 0, 0);
	private final static Color OUTPUT = new Color(0, 255, 0);

	public static class Result {

		public final BufferedImage image;
		public final int inputX;
		public final int inputY;
		public final int outputX;
		public final int outputY;

		Result(BufferedImage image, int inputX, int inputY, int outputX, int outputY) {
			this.image = image;
			this.inputX = inputX;
			this.inputY = inputY;
			this.outputX = outputX;
			this.outputY = outputY;
		}

	}

	private BufferedImage image;

	public Result readInputStream(InputStream input) throws IOException {
		this.image = ImageIO.read(input);

		final int[] inputPoint = findSinglePixelWithColor(INPUT, "input");
		final int[] outputPoint = findSinglePixelWithColor(OUTPUT, "output");

		return new Result(this.image, inputPoint[0], inputPoint[1], outputPoint[0], outputPoint[1]);
	}

	private int[] findSinglePixelWithColor(Color wantedColor, String wantedKey) throws IOException {
		final byte[] pixels = ((DataBufferByte) this.image.getRaster().getDataBuffer()).getData();
		final int width = this.image.getWidth();
		final int pixelLength = this.image.getAlphaRaster() == null ? 3 : 4;

		final int wantedRed = wantedColor.getRed();
		final int wantedGreen = wantedColor.getGreen();
		final int wantedBlue = wantedColor.getBlue();

		int[] result = null;

		for (int pixel = 0; pixel < pixels.length; pixel += pixelLength) {
			final int red = pixels[pixel + pixelLength - 1] & 0xff;
			final int green = pixels[pixel + pixelLength - 2] & 0xff;
			final int blue = pixels[pixel + pixelLength - 3] & 0xff;
			if (red == wantedRed && green == wantedGreen && blue == wantedBlue) {
				final int x = (pixel / pixelLength) % width;
				final int y = (pixel / pixelLength) / width;
				if (result != null)
					throw new IOException(
							"Too many " + wantedKey + "s (" + toHexString(wantedRed, wantedGreen, wantedBlue)
									+ ") defined: " + result[0] + "|" + result[1] + " and " + x + "|" + y);

				result = new int[]{x, y};
			}
		}

		if (result == null)
			throw new IOException(
					"No " + wantedKey + " (" + toHexString(wantedRed, wantedGreen, wantedBlue) + ") defined!");

		return result;
	}

	private static String toHexString(int wantedRed, int wantedGreen, int wantedBlue) {
		return String.format("#%02X%02X%02X", Integer.valueOf(wantedRed), Integer.valueOf(wantedGreen),
				Integer.valueOf(wantedBlue));
	}
}

package de.slothsoft.shera.dc;

/**
 * This class contains the metrics of the finished image after drawing with the
 * {@link WordPainter}.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.2.0
 */

public class WordPainterMetrics {

	final int width;
	final int height;
	final int startX;

	public WordPainterMetrics(int width, int height, int startX) {
		this.width = width;
		this.height = height;
		this.startX = startX;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getStartX() {
		return this.startX;
	}
}
package de.slothsoft.shera.dc;

/**
 * This class holds the information after drawing on the {@link DrawingContext} for how to
 * draw the next phonetic sound.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public class NextDrawing {

	int startPointX;
	int startPointY;
	boolean skipConnectionLine;

	public NextDrawing(int startPointX, int startPointY) {
		this.startPointX = startPointX;
		this.startPointY = startPointY;
	}

	public boolean isSkipConnectionLine() {
		return this.skipConnectionLine;
	}

	public NextDrawing skipConnectionLine(boolean newSkipConnectionLine) {
		setSkipConnectionLine(newSkipConnectionLine);
		return this;
	}

	public void setSkipConnectionLine(boolean skipConnectionLine) {
		this.skipConnectionLine = skipConnectionLine;
	}

	public int getStartPointX() {
		return this.startPointX;
	}

	public NextDrawing startPointX(int newStartPointX) {
		setStartPointX(newStartPointX);
		return this;
	}

	public void setStartPointX(int startPointX) {
		this.startPointX = startPointX;
	}

	public int getStartPointY() {
		return this.startPointY;
	}

	public NextDrawing startPointY(int newStartPointY) {
		setStartPointY(newStartPointY);
		return this;
	}

	public void setStartPointY(int startPointY) {
		this.startPointY = startPointY;
	}

}

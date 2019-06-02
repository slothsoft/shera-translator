package de.slothsoft.shera.dc;

import java.util.Objects;

/**
 * The context that <b>single</b> sounds can be painted on.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public class DrawingContext {

	public static final int PREF_SYMBOL_SIZE = 50;

	Canvas canvas;
	int width = PREF_SYMBOL_SIZE;
	int height = PREF_SYMBOL_SIZE;

	public DrawingContext(Canvas canvas) {
		canvas(canvas);
	}

	public NextDrawing createNextDrawing() {
		return new NextDrawing(0, this.height);
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public DrawingContext canvas(Canvas newCanvas) {
		setCanvas(newCanvas);
		return this;
	}

	public void setCanvas(Canvas canvas) {
		Objects.requireNonNull(canvas);
		this.canvas = canvas;
	}

	public int getHeight() {
		return this.height;
	}

	public DrawingContext height(int newHeight) {
		setHeight(newHeight);
		return this;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return this.width;
	}

	public DrawingContext width(int newWidth) {
		setWidth(newWidth);
		return this;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}

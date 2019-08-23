package de.slothsoft.shera.dc;

import java.util.Objects;

/**
 * An abstract class for painting on different surfaces with all methods that are common.
 * Note that I'd rather have default methods on the interface, but that didn't work with
 * the JavaScript parser.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.4.0
 */

public abstract class AbstractCanvas implements Canvas {

	private String background;
	private String foreground;
	private int[] size = {DrawingContext.PREF_SYMBOL_SIZE, DrawingContext.PREF_SYMBOL_SIZE};

	@Override
	public void fillOvalWithForeground(int x1, int y1, int x2, int y2) {
		swapColors();
		try {
			fillOval(x1, y1, x2, y2);
		} finally {
			swapColors();
		}
	}

	@Override
	public void fillRectangleWithForeground(int x1, int y1, int x2, int y2) {
		swapColors();
		try {
			fillRectangle(x1, y1, x2, y2);
		} finally {
			swapColors();
		}
	}

	@Override
	public void fillTriangleWithForeground(int x1, int y1, int x2, int y2, int x3, int y3) {
		swapColors();
		try {
			fillTriangle(x1, y1, x2, y2, x3, y3);
		} finally {
			swapColors();
		}
	}

	@Override
	public void swapColors() {
		final String color = getBackground();
		setBackground(getForeground());
		setForeground(color);
	}

	@Override
	public String getBackground() {
		return this.background;
	}

	@Override
	public void setBackground(String background) {
		this.background = background;
	}

	@Override
	public String getForeground() {
		return this.foreground;
	}

	@Override
	public void setForeground(String foreground) {
		this.foreground = foreground;
	}

	@Override
	public int getWidth() {
		return getSize()[0];
	}

	@Override
	public int getHeight() {
		return getSize()[1];
	}

	@Override
	public int[] getSize() {
		return this.size;
	}

	public AbstractCanvas size(int[] newSize) {
		setSize(newSize);
		return this;
	}

	public void setSize(int[] size) {
		Objects.requireNonNull(size);
		this.size = size;
	}

}

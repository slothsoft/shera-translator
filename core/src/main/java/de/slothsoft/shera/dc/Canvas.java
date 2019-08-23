package de.slothsoft.shera.dc;

/**
 * An interface for painting on different surfaces.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public interface Canvas {

	/**
	 * @since 0.4.0
	 */

	void fillOvalWithForeground(int x1, int y1, int x2, int y2);

	void fillOval(int x1, int y1, int x2, int y2);

	void drawOval(int x1, int y1, int x2, int y2);

	/**
	 * @since 0.4.0
	 */

	void fillRectangleWithForeground(int x1, int y1, int x2, int y2);

	void fillRectangle(int x1, int y1, int x2, int y2);

	void drawRectangle(int x1, int y1, int x2, int y2);

	/**
	 * @since 0.4.0
	 */

	void fillTriangleWithForeground(int x1, int y1, int x2, int y2, int x3, int y3);

	void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

	void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

	void drawLine(int x1, int y1, int x2, int y2);

	void translate(int x, int y);

	/**
	 * Swaps {@link #getBackground()} and {@link #getForeground()}.
	 *
	 * @since 0.4.0
	 */

	void swapColors();

	/**
	 * Returns the color that is used for every method {@code fillXyz()}.
	 *
	 * @return the background color
	 * @since 0.4.0
	 */

	String getBackground();

	/**
	 * Sets the color that is used for every method {@code fillXyz()}.
	 *
	 * @param background the background color
	 * @since 0.4.0
	 */

	void setBackground(String background);

	/**
	 * Returns the color that is used for every method {@code drawAbc()}.
	 *
	 * @return the foreground color
	 * @since 0.4.0
	 */

	String getForeground();

	/**
	 * Sets the color that is used for every method {@code drawAbc()}.
	 *
	 * @param foreground the foreground color
	 * @since 0.4.0
	 */

	void setForeground(String foreground);

	/**
	 * Returns the size of the canvas.
	 *
	 * @return array where index 0 is width and index 1 is height
	 * @since 0.4.0
	 * @see #getWidth()
	 * @see #getHeight()
	 */

	int[] getSize();

	/**
	 * Returns the width of the canvas.
	 *
	 * @return width
	 * @since 0.4.0
	 * @see #getSize()
	 */

	int getWidth();

	/**
	 * Returns the height of the canvas.
	 *
	 * @return height
	 * @since 0.4.0
	 * @see #getSize()
	 */

	int getHeight();
}

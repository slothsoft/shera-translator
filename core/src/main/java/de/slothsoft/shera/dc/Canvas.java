package de.slothsoft.shera.dc;

/**
 * An interface for painting on different surfaces.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public interface Canvas {

	void fillOval(int x1, int y1, int x2, int y2);

	void drawOval(int x1, int y1, int x2, int y2);

	void fillRectangle(int x1, int y1, int x2, int y2);

	void drawRectangle(int x1, int y1, int x2, int y2);

	void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

	void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

	void drawLine(int x1, int y1, int x2, int y2);

	void translate(int x, int y);

}

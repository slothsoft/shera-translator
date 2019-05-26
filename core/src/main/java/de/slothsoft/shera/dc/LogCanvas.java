package de.slothsoft.shera.dc;

import java.util.ArrayList;
import java.util.List;

/**
 * A default implementation for {@link Canvas} that just logs its method calls.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public class LogCanvas implements Canvas {

	private final List<String> log = new ArrayList<>();

	@Override
	public void fillOval(int x1, int y1, int x2, int y2) {
		this.log.add("fill-oval " + point(x1, y1) + " -> " + point(x2, y2));
	}

	@Override
	public void drawOval(int x1, int y1, int x2, int y2) {
		this.log.add("oval " + point(x1, y1) + " -> " + point(x2, y2));
	}

	@Override
	public void fillRectangle(int x1, int y1, int x2, int y2) {
		this.log.add("fill-rect " + point(x1, y1) + " -> " + point(x2, y2));
	}

	@Override
	public void drawRectangle(int x1, int y1, int x2, int y2) {
		this.log.add("rect " + point(x1, y1) + " -> " + point(x2, y2));
	}

	@Override
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.log.add("fill-triangle " + point(x1, y1) + " -> " + point(x2, y2) + " -> " + point(x3, y3));
	}

	@Override
	public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.log.add("triangle " + point(x1, y1) + " -> " + point(x2, y2) + " -> " + point(x3, y3));
	}
	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		this.log.add("line " + point(x1, y1) + " -> " + point(x2, y2));
	}

	private static String point(int x, int y) {
		return x + "|" + y;
	}

	public List<String> getLog() {
		return this.log;
	}
}

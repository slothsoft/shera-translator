package de.slothsoft.shera.javaapp;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;

import de.slothsoft.shera.Line;

public class LineDemo extends JFrame {

	private static final long serialVersionUID = 1020312435244619374L;
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private static final int BORDER = 10;
	private static final double SLOPE_MIN = -1;
	private static final double SLOPE_MAX = 1;
	private static final double SLOPE_STEPS = 0.1;
	private static final Color[] COLORS = {Color.YELLOW, Color.ORANGE, Color.RED, Color.MAGENTA, Color.BLUE, Color.CYAN,
			Color.GREEN};
	private static final NumberFormat FORMAT = new DecimalFormat("#.##");

	public static void main(String[] args) {
		final LineDemo lineDemo = new LineDemo();
		lineDemo.setVisible(true);
	}

	public LineDemo() {
		setTitle("Line Demo");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		final int startX = BORDER;
		final int startY = FRAME_HEIGHT / 2;
		final int middleX = FRAME_WIDTH / 2;
		final int endX = FRAME_WIDTH - 2 * BORDER;
		int colorIndex = 0;

		for (double slope = SLOPE_MIN; slope <= SLOPE_MAX; slope += SLOPE_STEPS) {
			g.setColor(COLORS[colorIndex]);
			final Line line = new Line().slope(slope);

			g.drawLine(startX, startY, startX + endX, startY + (int) line.calculateY(endX));
			colorIndex = (colorIndex + 1) % COLORS.length;

			g.drawString(FORMAT.format(slope), middleX, startY + (int) line.calculateY(middleX));
		}
	}

}

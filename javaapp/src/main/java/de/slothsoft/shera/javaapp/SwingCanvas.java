package de.slothsoft.shera.javaapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;

import de.slothsoft.shera.dc.AbstractCanvas;

public class SwingCanvas extends AbstractCanvas {

	private final Graphics context;
	private Color background = Color.WHITE;
	private Color foreground = Color.BLACK;

	public SwingCanvas(Graphics context) {
		this.context = Objects.requireNonNull(context);
		if (context instanceof Graphics2D) {
			((Graphics2D) context).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
	}

	@Override
	public void fillOval(int x1, int y1, int x2, int y2) {
		this.context.setColor(this.background);
		this.context.fillOval(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void drawOval(int x1, int y1, int x2, int y2) {
		this.context.setColor(this.foreground);
		this.context.drawOval(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void fillRectangle(int x1, int y1, int x2, int y2) {
		this.context.setColor(this.background);
		this.context.fillRect(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void drawRectangle(int x1, int y1, int x2, int y2) {
		this.context.setColor(this.foreground);
		this.context.drawRect(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.context.setColor(this.background);
		this.context.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
	}

	@Override
	public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.context.setColor(this.foreground);
		this.context.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		this.context.setColor(this.foreground);
		this.context.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void translate(int x, int y) {
		this.context.translate(x, y);
	}

	@Override
	public String getBackground() {
		return decode(this.background);
	}

	public SwingCanvas background(String newBackground) {
		setBackground(newBackground);
		return this;
	}

	@Override
	public void swapColors() {
		final Color color = this.background;
		this.background = this.foreground;
		this.foreground = color;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	@Override
	public void setBackground(String background) {
		this.background = Color.decode(background);
	}

	@Override
	public String getForeground() {
		return decode(this.foreground);
	}

	@SuppressWarnings("boxing")
	private static String decode(Color color) {
		return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}

	public SwingCanvas foreground(String newForeground) {
		setForeground(newForeground);
		return this;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	@Override
	public void setForeground(String foreground) {
		this.foreground = Color.decode(foreground);
	}

}

package de.slothsoft.shera.javaapp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;

import de.slothsoft.shera.dc.Canvas;

public class SwingCanvas implements Canvas {

	private final Graphics context;

	public SwingCanvas(Graphics context) {
		this.context = Objects.requireNonNull(context);
		if (context instanceof Graphics2D) {
			((Graphics2D) context).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
	}

	@Override
	public void fillOval(int x1, int y1, int x2, int y2) {
		this.context.fillOval(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void drawOval(int x1, int y1, int x2, int y2) {
		this.context.drawOval(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void fillRectangle(int x1, int y1, int x2, int y2) {
		this.context.fillRect(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void drawRectangle(int x1, int y1, int x2, int y2) {
		this.context.drawRect(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.context.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
	}

	@Override
	public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.context.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		this.context.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void translate(int x, int y) {
		this.context.translate(x, y);
	}

}

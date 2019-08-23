package de.slothsoft.shera.javaapp;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.dc.DrawingContext;
import de.slothsoft.shera.dc.LogCanvas;
import de.slothsoft.shera.dc.NextDrawing;

public class PhoneticSoundImage extends JLabel {

	private static final long serialVersionUID = 2549952882533179865L;

	PhoneticSound content;

	int symbolSize;
	final int border = 2;

	public PhoneticSoundImage(PhoneticSound content) {
		this.content = content;
		setSymbolSize(DrawingContext.PREF_SYMBOL_SIZE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintSoundOnGraphics(g, this.content, this.symbolSize, this.border);
	}

	static void paintSoundOnGraphics(Graphics graphics, PhoneticSound sound, int symbolSize, int border) {
		graphics.setColor(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		graphics.fillRect(0, 0, symbolSize, symbolSize);

		if (sound != null) {
			graphics.setColor(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
			final int nettoSymbolSize = symbolSize - 2 * border - 1;

			final NextDrawing nextDrawing = sound
					.drawOn(new DrawingContext(new LogCanvas()).width(nettoSymbolSize).height(nettoSymbolSize));

			final int translateX = border - nextDrawing.getStartPointX();
			final int translateY = border + ((nettoSymbolSize - nextDrawing.getStartPointY()) / 2);
			graphics.translate(translateX, translateY);

			final SwingCanvas canvas = new SwingCanvas(graphics);
			canvas.setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
			canvas.setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
			sound.drawOn(new DrawingContext(canvas).width(nettoSymbolSize).height(nettoSymbolSize));

			graphics.translate(-translateX, -translateY);
		}
	}

	public PhoneticSound getContent() {
		return this.content;
	}

	public PhoneticSoundImage content(PhoneticSound newContent) {
		setContent(newContent);
		return this;
	}

	public void setContent(PhoneticSound content) {
		this.content = content;
		repaint();
	}

	public int getSymbolSize() {
		return this.symbolSize;
	}

	public PhoneticSoundImage symbolSize(int newSymbolSize) {
		setSymbolSize(newSymbolSize);
		return this;
	}

	public void setSymbolSize(int symbolSize) {
		this.symbolSize = symbolSize;
		setPreferredSize(new Dimension(symbolSize, symbolSize));
		repaint();
	}

}

package de.slothsoft.shera.javaapp;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.dc.DrawingContext;

public class PhoneticSoundImage extends JLabel {

	private static final long serialVersionUID = 2549952882533179865L;

	PhoneticSound content;

	int symbolSize;

	public PhoneticSoundImage(PhoneticSound content) {
		this.content = content;
		setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
		setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		setSymbolSize(DrawingContext.PREF_SYMBOL_SIZE);
		setBorder(BorderFactory.createLineBorder(SheRaJavaApp.COLOR_BLACK));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		if (this.content != null) {
			g.setColor(getForeground());
			this.content.drawOn(new DrawingContext(new SwingCanvas(g)).height(this.symbolSize).width(this.symbolSize));
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

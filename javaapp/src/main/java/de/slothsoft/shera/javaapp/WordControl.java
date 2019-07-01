package de.slothsoft.shera.javaapp;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Objects;

import javax.swing.JPanel;

import de.slothsoft.shera.Word;
import de.slothsoft.shera.dc.WordPainter;

public class WordControl extends JPanel {

	private static final long serialVersionUID = -3185479415430023317L;

	Word[] content;

	public WordControl() {
		setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
		setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		if (this.content != null) {
			g.setColor(getForeground());

			final SwingCanvas canvas = new SwingCanvas(g);
			canvas.setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
			canvas.setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
			canvas.translate(2, 2);

			final WordPainter painter = new WordPainter(canvas);
			painter.paintWords(this.content);
		}
	}

	public Word[] getContent() {
		return this.content;
	}

	public WordControl content(Word[] newContent) {
		setContent(newContent);
		return this;
	}

	public void setContent(Word[] content) {
		this.content = Objects.requireNonNull(content);
		repaint();
	}

}
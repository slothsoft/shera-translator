package de.slothsoft.shera.javaapp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Objects;

import javax.swing.JPanel;

import de.slothsoft.shera.PhoneticSound;

public class OutputControl extends JPanel {

	private static final long serialVersionUID = -4836650964707954738L;

	static final Color BACKGROUND = new Color(38, 33, 93);
	static final Color FOREGROUND = Color.WHITE;

	PhoneticSound[] content;

	public OutputControl() {
		setBackground(BACKGROUND);
		setLayout(new FlowLayout());
	}

	private void createControls() {
		for (final PhoneticSound sound : this.content) {
			add(new PhoneticSoundControl(sound));
		}
	}

	public PhoneticSound[] getContent() {
		return this.content;
	}

	public OutputControl content(PhoneticSound[] newContent) {
		setContent(newContent);
		return this;
	}

	public void setContent(PhoneticSound[] content) {
		this.content = Objects.requireNonNull(content);
		removeAll();
		createControls();
		repaint();
		doLayout();
	}

}
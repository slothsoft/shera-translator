package de.slothsoft.shera.javaapp;

import java.awt.FlowLayout;
import java.util.Objects;

import javax.swing.JPanel;

import de.slothsoft.shera.PhoneticSound;

public class OutputControl extends JPanel {

	private static final long serialVersionUID = -4836650964707954738L;

	PhoneticSound[] content;

	public OutputControl() {
		setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
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
package de.slothsoft.shera.javaapp;

import java.awt.FlowLayout;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.slothsoft.shera.PhoneticSound;

public class SingleSoundsControl extends JPanel {

	private static final long serialVersionUID = -4836650964707954738L;

	PhoneticSound[] content;

	public SingleSoundsControl() {
		setBackground(SheRaJavaApp.COLOR_WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createLineBorder(SheRaJavaApp.COLOR_BLACK));
	}

	private void createControls() {
		for (final PhoneticSound sound : this.content) {
			add(new PhoneticSoundControl(sound));
		}
	}

	public PhoneticSound[] getContent() {
		return this.content;
	}

	public SingleSoundsControl content(PhoneticSound[] newContent) {
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
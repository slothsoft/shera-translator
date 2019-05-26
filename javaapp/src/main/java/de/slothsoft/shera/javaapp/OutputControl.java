package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.slothsoft.shera.PhoneticSound;

public class OutputControl extends JPanel {

	private static final long serialVersionUID = -4836650964707954738L;

	final JTextArea input = new JTextArea();

	PhoneticSound[] content;

	public OutputControl() {
		setLayout(new FlowLayout());
		add(this.input, BorderLayout.CENTER);
	}

	private void createControls() {
		for (final PhoneticSound sound : this.content) {
			add(new JLabel(sound.name()));
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
	}

}
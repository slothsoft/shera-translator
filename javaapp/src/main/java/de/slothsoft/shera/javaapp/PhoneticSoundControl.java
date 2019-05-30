package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.slothsoft.shera.PhoneticSound;

public class PhoneticSoundControl extends JPanel {

	private static final long serialVersionUID = 6741187963767128512L;

	PhoneticSound content;

	public PhoneticSoundControl(PhoneticSound content) {
		this.content = content;

		setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		setLayout(new BorderLayout());

		add(new PhoneticSoundImage(content), BorderLayout.CENTER);
		add(new PhoneticSoundLabel(content), BorderLayout.SOUTH);
	}

	public PhoneticSound getContent() {
		return this.content;
	}

	/*
	 *
	 */

	static class PhoneticSoundLabel extends JLabel {

		private static final long serialVersionUID = 6826229287460889693L;

		public PhoneticSoundLabel(PhoneticSound content) {
			super(content.getDisplayName(), SwingConstants.CENTER);
			setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
		}

	}
}
package de.slothsoft.shera.javaapp;

import java.awt.GridLayout;

import javax.swing.JFrame;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.WordSpliterator;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 6489580285966352151L;

	private final InputControl inputControl = new InputControl().content("Shee-Rah");
	private final OutputControl outputControl = new OutputControl();

	public MainFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("She-Ra Translator " + Messages.getString("Version"));
		createControls();
		setSize(800, 400);
		setLocationRelativeTo(null);
	}

	private void createControls() {
		setLayout(new GridLayout(1, 2));

		add(this.inputControl);
		add(this.outputControl);

		this.inputControl.onModify(this::translate);
		translate();
	}

	void translate() {
		final WordSpliterator spliterator = new WordSpliterator(this.inputControl.getSelectedSoundMapper());
		final PhoneticSound[] sounds = spliterator.split(this.inputControl.getContent());
		this.outputControl.setContent(sounds);
	}

}
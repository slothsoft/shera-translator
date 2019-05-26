package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InputControl extends JPanel {

	private static final long serialVersionUID = -4836650964707954738L;

	final JTextArea input = new JTextArea();

	public InputControl() {
		setLayout(new BorderLayout());
		add(this.input, BorderLayout.CENTER);
	}

	public String getContent() {
		return this.input.getText();
	}

	public InputControl content(String newContent) {
		setContent(newContent);
		return this;
	}

	public void setContent(String content) {
		this.input.setText(content);
	}

}
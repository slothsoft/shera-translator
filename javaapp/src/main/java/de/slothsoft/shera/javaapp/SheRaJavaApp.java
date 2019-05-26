package de.slothsoft.shera.javaapp;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SheRaJavaApp {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final Exception ex) {
			System.err.println(ex.getMessage());
		}
		SwingUtilities.invokeLater(() -> createAndShowGui());
	}

	private static void createAndShowGui() {
		final MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

}
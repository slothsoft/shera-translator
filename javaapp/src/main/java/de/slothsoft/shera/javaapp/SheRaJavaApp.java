package de.slothsoft.shera.javaapp;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * I actually want to create something that's not a Java app. Still for debugging purposes
 * I created this app.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

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
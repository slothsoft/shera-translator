package de.slothsoft.shera.javaapp;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

	static final Color COLOR_BLACK = new Color(34, 34, 34);
	static final Color COLOR_SCRIPT_BACKGROUND = new Color(38, 33, 93);
	static final Color COLOR_SCRIPT_FOREGROUND = Color.WHITE;
	static final Color COLOR_WHITE = Color.WHITE;

	public static final String IMAGE_TITLE = "title.png";
	public static final String IMAGE_MENU = "menu.png";
	public static final String IMAGE_EXIT = "exit.png";
	static final String[] ALL_IMAGES = {IMAGE_TITLE, IMAGE_MENU, IMAGE_EXIT};

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

	public static ImageIcon fetchImageIcon(String imagePath) {
		return new ImageIcon(fetchImage(imagePath));
	}
	public static BufferedImage fetchImage(String imagePath) {
		try (InputStream input = SheRaJavaApp.class.getResourceAsStream(imagePath)) {
			return ImageIO.read(input);
		} catch (final IOException e) {
			throw new RuntimeException("Could not find image: " + imagePath, e);
		}
	}
}
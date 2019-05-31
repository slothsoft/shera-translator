package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.WordSpliterator;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 6489580285966352151L;

	static final int PADDING = 25;

	private JPanel contentPane = new JPanel();
	private final InputControl inputControl = new InputControl().content("Shee-Rah");
	private final OutputControl outputControl = new OutputControl();

	public MainFrame() {
		setContentPane(this.contentPane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setTitle("She-Ra Translator " + Messages.getString("Version"));

		this.contentPane.setBorder(BorderFactory.createLineBorder(SheRaJavaApp.COLOR_BLACK));

		createControls();
		setSize(800, 400);
		setLocationRelativeTo(null);
		hookListeners();
	}

	private void createControls() {
		setLayout(new BorderLayout());

		add(createHeader(), BorderLayout.NORTH);

		final JPanel content = new JPanel();
		content.setLayout(new GridLayout(1, 2, PADDING, PADDING));
		content.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
		content.setBackground(SheRaJavaApp.COLOR_WHITE);

		content.add(this.inputControl);
		content.add(this.outputControl);
		add(content, BorderLayout.CENTER);

		this.inputControl.onModify(this::translate);
		translate();
	}

	private JPanel createHeader() {
		final JPanel header = new JPanel();
		header.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		header.setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		header.setLayout(new BorderLayout());
		header.add(new JLabel(SheRaJavaApp.fetchImageIcon(SheRaJavaApp.IMAGE_TITLE)), BorderLayout.WEST);
		header.add(createTitle(), BorderLayout.CENTER);
		header.add(createToolBar(), BorderLayout.EAST);
		return header;
	}

	private static Component createTitle() {
		final JLabel result = new JLabel(Messages.getString("ApplicationTitle"));
		result.setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
		result.setFont(result.getFont().deriveFont(20f));
		return result;
	}

	private Component createToolBar() {
		final JToolBar result = new JToolBar();
		result.setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		result.setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
		result.setFloatable(false);

		result.add(
				new AbstractAction(Messages.getString("Exit"), SheRaJavaApp.fetchImageIcon(SheRaJavaApp.IMAGE_EXIT)) {
					private static final long serialVersionUID = 2936288215707439825L;

					@Override
					public void actionPerformed(ActionEvent e) {
						MainFrame.this.dispose();
					}
				});

		for (final Component child : result.getComponents()) {
			child.setBackground(result.getBackground());
		}

		return result;
	}

	void translate() {
		final WordSpliterator spliterator = new WordSpliterator(this.inputControl.getSelectedSoundMapper());
		final PhoneticSound[] sounds = spliterator.split(this.inputControl.getContent());
		this.outputControl.setContent(sounds);
	}

	private void hookListeners() {
		Point position = new Point();
		this.contentPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				position.setLocation(e.getX(), e.getY());
			}
		});
		this.contentPane.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				Rectangle rectangle = getBounds();
				setBounds(evt.getXOnScreen() - position.x, evt.getYOnScreen() - position.y, rectangle.width,
						rectangle.height);
			}
		});
	}

}
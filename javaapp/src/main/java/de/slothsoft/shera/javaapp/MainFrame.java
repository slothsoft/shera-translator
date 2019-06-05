package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.text.MessageFormat;
import java.util.function.Consumer;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;

import de.slothsoft.shera.WordSpliterator;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 6489580285966352151L;

	static final int PADDING = 25;
	static final ImageIcon ICON_MENU = SheRaJavaApp.fetchImageIcon(SheRaJavaApp.IMAGE_MENU);

	static class DefaultAction extends AbstractAction {

		private static final long serialVersionUID = 6211678626573664146L;

		private final Consumer<ActionEvent> action;

		public DefaultAction(String name, Icon icon, Consumer<ActionEvent> action) {
			super(name, icon);
			this.action = action;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.action.accept(e);
		}
	}

	static enum Link {
		GITHUB_PROJECT, REPORT_ISSUE;

		String getDisplayName() {
			return Messages.getString("Link." + name() + ".displayName");
		}

		String getUrl() {
			return Messages.getString("Link." + name() + ".url");
		}
	}

	class LinkCellRenderer implements ListCellRenderer<Link> {

		private final JLabel link = new JLabel();

		public LinkCellRenderer() {
			this.link.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
			this.link.setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList list, Link value, int index, boolean isSelected,
				boolean cellHasFocus) {
			this.link.setBackground(new Color(217, 219, 241));
			this.link.setText(value.getDisplayName());
			return this.link;
		}
	}

	private final JPanel contentPane = new JPanel();
	private final InputControl inputControl = new InputControl().content("Shee-Rah");
	private final SingleSoundsControl singleSoundsControl = new SingleSoundsControl();
	private final WordControl wordControl = new WordControl();

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
		content.add(createOutputControls());
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

		final JComboBox<Link> linkMenu = new JComboBox<Link>(Link.values()) {

			private static final long serialVersionUID = 5073456540973182062L;

			@Override
			public synchronized Dimension getSize() {
				return new Dimension(200, 200); // makes the popup bigger
			}

			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				g.drawImage(ICON_MENU.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};
		linkMenu.setRenderer(new LinkCellRenderer());
		linkMenu.setPreferredSize(new Dimension(ICON_MENU.getIconWidth() + 8, ICON_MENU.getIconHeight()));
		linkMenu.addActionListener(e -> openLink((Link) linkMenu.getSelectedItem()));
		result.add(linkMenu);

		result.addSeparator();
		result.add(new DefaultAction(Messages.getString("Exit"), SheRaJavaApp.fetchImageIcon(SheRaJavaApp.IMAGE_EXIT),
				e -> dispose()));

		for (final Component child : result.getComponents()) {
			child.setBackground(result.getBackground());
		}

		return result;
	}

	private static void openLink(Link selectedItem) {
		final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(new URI(selectedItem.getUrl()));
				return;
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null,
				MessageFormat.format(Messages.getString("NoBrowseMessage"), selectedItem.getUrl()),
				Messages.getString("NoBrowseTitle"), JOptionPane.INFORMATION_MESSAGE);
	}

	private Component createOutputControls() {
		final JTabbedPane result = new JTabbedPane();
		result.addTab(Messages.getString("SingleSounds"), this.singleSoundsControl);
		result.addTab(Messages.getString("Word"), this.wordControl);
		return result;
	}

	void translate() {
		final WordSpliterator spliterator = new WordSpliterator(this.inputControl.getSelectedSoundMapper());
		this.singleSoundsControl.setContent(spliterator.splitIntoSounds(this.inputControl.getContent()));
		this.wordControl.setContent(spliterator.splitIntoWords(this.inputControl.getContent()));
	}

	private void hookListeners() {
		final Point position = new Point();
		this.contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				position.setLocation(e.getX(), e.getY());
			}
		});
		this.contentPane.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				final Rectangle rectangle = getBounds();
				setBounds(evt.getXOnScreen() - position.x, evt.getYOnScreen() - position.y, rectangle.width,
						rectangle.height);
			}
		});
	}

}
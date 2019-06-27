package de.slothsoft.shera.javaapp.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Accordion extends JPanel {

	private static final long serialVersionUID = 1774118395946561703L;

	private static final int DEFAULT_ARROW_HEIGHT = 11;
	private static final int DEFAULT_ARROW_WIDTH = 5;

	private final List<Page> pages = new ArrayList<>();
	private final JLabel spanner = new JLabel();
	private final GridBagData spannerData = GridBagData.forPanel(0, 0);

	boolean defaultExpanded = true;
	int headerHeight = 20;
	private Image openImage;
	private Image closedImage;
	private BiConsumer<Page, Graphics> pageHeaderPainter = Page::defaultPaintComponent;

	public Accordion() {
		setLayout(new GridBagLayout());
		this.closedImage = createClosedImage(DEFAULT_ARROW_WIDTH, DEFAULT_ARROW_HEIGHT);
		this.openImage = createOpenImage(DEFAULT_ARROW_HEIGHT, DEFAULT_ARROW_WIDTH);
	}

	private static Image createClosedImage(int w, int h) {
		final BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D graphics = createGraphics(result);

		final int[] x = {0, w - 1, 0};
		final int[] y = {0, h / 2, h - 1};
		drawArrow(graphics, new Polygon(x, y, 3));
		graphics.dispose();
		return result;
	}

	private static Graphics2D createGraphics(BufferedImage result) {
		final Graphics2D graphics = result.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setPaint(new Color(0, 0, 0, 0));
		graphics.fillRect(0, 0, result.getWidth(), result.getHeight());
		return graphics;
	}

	private static void drawArrow(Graphics2D graphics, Polygon arrow) {
		graphics.setPaint(Color.DARK_GRAY.brighter());
		graphics.fill(arrow);
		graphics.setPaint(Color.BLACK);
		graphics.draw(arrow);
	}

	private static Image createOpenImage(int w, int h) {
		final BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D graphics = createGraphics(result);

		final int[] x = {0, w / 2, w - 1};
		final int[] y = {0, h - 1, 0};
		drawArrow(graphics, new Polygon(x, y, 3));
		graphics.dispose();
		return result;
	}

	public Page addPage(String title, JComponent content) {
		final Page page = new Page(title, content);
		addPage(page);
		return page;
	}

	void addPage(Page page) {
		add(page, createNextGridBagData());
		add(page.getContent(), createNextGridBagData());

		page.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				page.toggleExpanded();
			}
		});
		this.pages.add(page);

		// to fill the remaining space
		remove(this.spanner);
		add(this.spanner, this.spannerData.gridy(getComponentCount()));
	}

	private GridBagData createNextGridBagData() {
		return GridBagData.forLabel(0, getComponentCount()).insets(0).fill(GridBagConstraints.HORIZONTAL);
	}

	public Page[] getPages() {
		return this.pages.toArray(new Page[this.pages.size()]);
	}

	public boolean isDefaultExpanded() {
		return this.defaultExpanded;
	}

	public Accordion defaultExpanded(boolean newDefaultExpanded) {
		setDefaultExpanded(newDefaultExpanded);
		return this;
	}

	public void setDefaultExpanded(boolean defaultExpanded) {
		this.defaultExpanded = defaultExpanded;
	}

	public int getHeaderHeight() {
		return this.headerHeight;
	}

	public Accordion headerHeight(int newHeaderHeight) {
		setHeaderHeight(newHeaderHeight);
		return this;
	}

	public void setHeaderHeight(int headerHeight) {
		this.headerHeight = headerHeight;
		this.pages.forEach(page -> page.setHeaderHeight(headerHeight));
		doLayout();
	}

	public Image getOpenImage() {
		return this.openImage;
	}

	public Accordion openImage(Image newOpenImage) {
		setOpenImage(newOpenImage);
		return this;
	}

	public void setOpenImage(Image openImage) {
		this.openImage = Objects.requireNonNull(openImage);
	}

	public Image getClosedImage() {
		return this.closedImage;
	}

	public Accordion closedImage(Image newClosedImage) {
		setClosedImage(newClosedImage);
		return this;
	}

	public void setClosedImage(Image closedImage) {
		this.closedImage = Objects.requireNonNull(closedImage);
	}

	public BiConsumer<Page, Graphics> getPageHeaderPainter() {
		return this.pageHeaderPainter;
	}

	public Accordion pageHeaderPainter(BiConsumer<Page, Graphics> newPageHeaderPainter) {
		setPageHeaderPainter(newPageHeaderPainter);
		return this;
	}

	public void setPageHeaderPainter(BiConsumer<Page, Graphics> pageHeaderPainter) {
		this.pageHeaderPainter = Objects.requireNonNull(pageHeaderPainter);
	}

	/*
	 *
	 */

	public class Page extends JPanel {

		private static final long serialVersionUID = 4956576344519309900L;

		private final JComponent content;
		private String title;
		private boolean expanded;

		public Page(String title, JComponent content) {
			this.title = Objects.requireNonNull(title);
			this.content = Objects.requireNonNull(content);

			setBorder(BorderFactory.createRaisedBevelBorder());
			setRequestFocusEnabled(true);
			setExpanded(Accordion.this.defaultExpanded);
			setHeaderHeight(Accordion.this.headerHeight);
		}

		void setHeaderHeight(int headerHeight) {
			setPreferredSize(new Dimension(100, headerHeight));
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			getPageHeaderPainter().accept(this, g);
		}

		void defaultPaintComponent(Graphics g) {
			g.setFont(Accordion.this.getFont());
			if (g instanceof Graphics2D) {
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			}

			final Insets borderInsets = getBorder().getBorderInsets(this);
			final int y = (g.getFontMetrics().getHeight() + Accordion.this.headerHeight - borderInsets.top
					- borderInsets.bottom) / 2;
			int x = borderInsets.left;

			if (isExpanded()) {
				final Image image = getOpenImage();
				drawImage(g, image, x);
				x += x + image.getWidth(null);
			} else {
				final Image image = getClosedImage();
				drawImage(g, image, x);
				x += x + image.getWidth(null);
			}

			g.drawString(this.title, x, y);
		}

		private void drawImage(Graphics g, Image image, int x) {
			final Insets borderInsets = getBorder().getBorderInsets(this);
			final int y = (Accordion.this.headerHeight - borderInsets.top - borderInsets.bottom - image.getHeight(null))
					/ 2;
			g.drawImage(image, x, borderInsets.top + y, null);
		}

		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
			this.title = title;
			repaint();
		}

		public JComponent getContent() {
			return this.content;
		}

		public boolean isExpanded() {
			return this.expanded;
		}

		public void toggleExpanded() {
			setExpanded(!this.expanded);
		}

		public Page expanded(boolean newExpanded) {
			setExpanded(newExpanded);
			return this;
		}

		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
			this.content.setVisible(expanded);
			repaint();
		}

	}

}
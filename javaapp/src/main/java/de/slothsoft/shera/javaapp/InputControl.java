package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.Locale;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.slothsoft.shera.SoundMapper;
import de.slothsoft.shera.dc.WordPainterConfig;
import de.slothsoft.shera.dc.WordPainterConfig.WritingSystem;
import de.slothsoft.shera.javaapp.common.Accordion;
import de.slothsoft.shera.javaapp.common.GridBagData;

public class InputControl extends JPanel {

	private static final long serialVersionUID = -4836650964707954738L;

	final JComboBox<SoundMapper> soundMapperCombo = new JComboBox<>(
			SoundMapper.createAll().stream().toArray(SoundMapper[]::new));
	final JComboBox<WritingSystem> writingSystemCombo = new JComboBox<>(WritingSystem.values());
	final JTextArea input = new JTextArea();

	Runnable onModify = () -> { // nothing to do on default
	};

	public InputControl() {
		setLayout(new BorderLayout(MainFrame.PADDING, MainFrame.PADDING));
		setBackground(SheRaJavaApp.COLOR_WHITE);

		this.input.setBorder(BorderFactory.createLineBorder(SheRaJavaApp.COLOR_BLACK));

		add(this.input, BorderLayout.CENTER);
		add(createSettingControls(), BorderLayout.SOUTH);

		hookListeners();
	}

	private Component createSettingControls() {
		final Accordion result = new Accordion();

		final JPanel controls = new JPanel();
		controls.setLayout(new GridBagLayout());
		controls.setBorder(BorderFactory.createLineBorder(SheRaJavaApp.COLOR_BLACK));

		int y = 0;

		controls.add(new JLabel(Messages.getString("Language") + ':'), GridBagData.forLabel(0, y));
		this.soundMapperCombo.setRenderer(new LocaleCellRenderer());
		this.soundMapperCombo.setMaximumSize(this.soundMapperCombo.getMinimumSize());
		controls.add(this.soundMapperCombo, GridBagData.forControl(1, y));
		y++;

		controls.add(new JLabel(Messages.getString("WritingSystem") + ':'), GridBagData.forLabel(0, y));
		this.writingSystemCombo.setRenderer(new WritingSystemCellRenderer());
		this.writingSystemCombo.setMaximumSize(this.writingSystemCombo.getMinimumSize());
		controls.add(this.writingSystemCombo, GridBagData.forControl(1, y));
		y++;

		controls.add(new JLabel(), GridBagData.forLabel(0, y));
		final JButton button = new JButton(Messages.getString("Sounds"));
		button.addActionListener(e -> {
			final PhoneticSoundsDialog dialog = new PhoneticSoundsDialog(getParent(), getSelectedSoundMapper());
			dialog.setVisible(true);
		});
		controls.add(button, GridBagData.forControl(1, y));
		y++;

		final Accordion.Page page = result.addPage(Messages.getString("Settings"), controls);
		page.setBackground(SheRaJavaApp.COLOR_SCRIPT_BACKGROUND);
		page.setForeground(SheRaJavaApp.COLOR_SCRIPT_FOREGROUND);
		page.setBorder(BorderFactory.createLineBorder(SheRaJavaApp.COLOR_BLACK));

		return result;
	}

	private void hookListeners() {
		this.soundMapperCombo.addActionListener(e -> getOnModify().run());
		this.writingSystemCombo.addActionListener(e -> getOnModify().run());
		this.input.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				getOnModify().run();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				getOnModify().run();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				getOnModify().run();
			}
		});
	}

	public SoundMapper getSelectedSoundMapper() {
		return (SoundMapper) this.soundMapperCombo.getSelectedItem();
	}

	public WordPainterConfig getSelectedWordPainterConfig() {
		return new WordPainterConfig().writingSystem((WritingSystem) this.writingSystemCombo.getSelectedItem());
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

	public Runnable getOnModify() {
		return this.onModify;
	}

	public InputControl onModify(Runnable newOnModify) {
		setOnModify(newOnModify);
		return this;
	}

	public void setOnModify(Runnable onModify) {
		this.onModify = Objects.requireNonNull(onModify);
	}

	/*
	 *
	 */

	static class LocaleCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = -8675510219361827516L;

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			final JLabel result = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
			result.setText(Locale.forLanguageTag(((SoundMapper) value).getLocale()).getDisplayLanguage());
			return result;
		}
	}

	static class WritingSystemCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = -1026291514517062981L;

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			final JLabel result = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
			switch ((WritingSystem) value) {
				case LEFT_TO_RIGHT :
					result.setText(Messages.getString("WritingSystem.LEFT_TO_RIGHT"));
					break;
				case RIGHT_TO_LEFT :
					result.setText(Messages.getString("WritingSystem.RIGHT_TO_LEFT"));
					break;
				default :
					result.setText(value.toString());
			}
			return result;
		}
	}

}
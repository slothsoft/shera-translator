package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Objects;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.slothsoft.shera.SoundMapper;

public class InputControl extends JPanel {

	private static final long serialVersionUID = -4836650964707954738L;

	final JToolBar toolbar = new JToolBar();
	final JComboBox<SoundMapper> soundMapperCombo = new JComboBox<>(
			SoundMapper.createAll().stream().toArray(SoundMapper[]::new));
	final JTextArea input = new JTextArea();

	Runnable onModify = () -> { // nothing to do on default
	};

	public InputControl() {
		setLayout(new BorderLayout());
		createControls();

		add(this.toolbar, BorderLayout.NORTH);
		add(this.input, BorderLayout.CENTER);
	}

	private void createControls() {
		this.toolbar.setBackground(OutputControl.BACKGROUND);
		this.toolbar.setForeground(OutputControl.FOREGROUND);
		this.toolbar.setFloatable(false);

		this.soundMapperCombo.setRenderer(new LocaleCellRenderer());
		this.soundMapperCombo.setMaximumSize(this.soundMapperCombo.getMinimumSize());
		this.toolbar.add(this.soundMapperCombo);

		this.toolbar.addSeparator();

		final JButton button = new JButton(Messages.getString("Sounds"));
		button.addActionListener(e -> {
			final PhoneticSoundsDialog dialog = new PhoneticSoundsDialog(getParent(), getSelectedSoundMapper());
			dialog.setVisible(true);
		});
		this.toolbar.add(button);

		// listeners
		this.soundMapperCombo.addActionListener(e -> getOnModify().run());
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
			result.setText(((SoundMapper) value).getLocale().getDisplayLanguage());
			return result;
		}
	}

}
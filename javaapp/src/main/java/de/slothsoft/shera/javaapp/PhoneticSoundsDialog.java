package de.slothsoft.shera.javaapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.SoundMapper;
import de.slothsoft.shera.dc.DrawingContext;

public class PhoneticSoundsDialog extends JDialog {
	private static final long serialVersionUID = -3451564198052089959L;
	public static final int SYMBOL_SIZE = DrawingContext.PREF_SYMBOL_SIZE;

	public PhoneticSoundsDialog(Container container, SoundMapper soundMapper) {
		setTitle(Messages.getString("PhoneticSounds")); //$NON-NLS-1$
		createControls(soundMapper);
		setSize(400, 600);
		setLocationRelativeTo(container);
	}

	private void createControls(SoundMapper soundMapper) {
		setLayout(new BorderLayout());

		final JTable table = new JTable();
		table.setModel(new SoundsModel(soundMapper));
		table.setRowHeight(SYMBOL_SIZE);
		table.setDefaultRenderer(PhoneticSound.class, new PhoneticSoundImageRenderer());

		this.add(new JScrollPane(table), BorderLayout.CENTER);
	}

	/*
	 *
	 */

	static class SoundsModel extends AbstractTableModel {

		private static final long serialVersionUID = -6192080346000404736L;
		private static final String[] COLUMNS = {Messages.getString("Image"), Messages.getString("Sound"),
				Messages.getString("Example"), Messages.getString("MapsTo")};
		private static final PhoneticSound[] SOUNDS = PhoneticSound.values();
		private static final int COL_IMAGE = 0;
		private static final int COL_SOUND = 1;
		private static final int COL_EXAMPLE = 2;
		private static final int COL_MAPS_TO = 3;

		private final Map<PhoneticSound, Set<String>> soundToLetters = new HashMap<>();

		public SoundsModel(SoundMapper soundMapper) {
			for (final String supportedLetter : soundMapper.getSupportedLetters()) {
				for (final PhoneticSound sound : soundMapper.getSounds(supportedLetter)) {
					final Set<String> letters = this.soundToLetters.computeIfAbsent(sound, key -> new TreeSet<>());
					letters.add(supportedLetter);
				}
			}
		}

		@Override
		public int getColumnCount() {
			return COLUMNS.length;
		}

		@Override
		public String getColumnName(int column) {
			return COLUMNS[column];
		}

		@Override
		public int getRowCount() {
			return SOUNDS.length;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == COL_IMAGE) return PhoneticSound.class;
			return super.getColumnClass(columnIndex);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
				case COL_IMAGE :
					return SOUNDS[rowIndex];
				case COL_SOUND :
					return SOUNDS[rowIndex].getDisplayName();
				case COL_EXAMPLE :
					return SOUNDS[rowIndex].getExample();
				case COL_MAPS_TO :
					return this.soundToLetters.get(SOUNDS[rowIndex]);
				default :
					return null;
			}
		}

	}

	/*
	 *
	 */

	static class PhoneticSoundImageRenderer extends PhoneticSoundImage implements TableCellRenderer {

		private static final long serialVersionUID = 9139498397904683061L;
		private static final int BORDER = 5;

		public PhoneticSoundImageRenderer() {
			super(null);
			setSymbolSize(SYMBOL_SIZE - 2 * BORDER);
			setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setContent((PhoneticSound) value);
			return this;
		}

	}
}
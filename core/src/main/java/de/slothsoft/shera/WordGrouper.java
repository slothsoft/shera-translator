package de.slothsoft.shera;

import java.util.Arrays;
import java.util.Objects;

/**
 * Groups a {@link Word} into groups that can be drawn beautifully.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.4.0
 */

public class WordGrouper {

	static final int MALUS_NO_END = 5;
	static final int MALUS_TOO_SMALL = 10;
	static final int MALUS_TOO_BIG = 10;

	static final int[] EMPTY_INT_ARRAY = new int[0];

	private WordGrouperConfig config = new WordGrouperConfig();

	int[] preferredIndexMap;

	public WordGrouper() {
		recalculatePreferredIndexMap();
	}

	private void recalculatePreferredIndexMap() {
		final Integer[] bigMap = new Integer[this.config.maxGroupSize - this.config.minGroupSize + 1];
		for (int i = 0; i < bigMap.length; i++) {
			bigMap[i] = Integer.valueOf(this.config.minGroupSize + i);
		}
		Arrays.sort(bigMap, (i1, i2) -> {
			int compare = Math.abs(this.config.preferredGroupSize - i1.intValue())
					- Math.abs(this.config.preferredGroupSize - i2.intValue());

			if (compare == 0) {
				compare = i2.compareTo(i1);
			}
			return compare;
		});
		this.preferredIndexMap = new int[bigMap.length];
		for (int i = 0; i < this.preferredIndexMap.length; i++) {
			this.preferredIndexMap[i] = bigMap[i].intValue();
		}
	}

	public Word groupWord(PhoneticSound... sounds) {
		return groupWord(new Word(sounds));
	}

	public Word groupWord(Word word) {
		final PossibleResult result = groupWord(word.content, 0);
		if (result != null) {
			word.groupSeparatorIndexes = result.groupSeparatorIndexes;
		}
		return word;
	}

	private PossibleResult groupWord(PhoneticSound[] sounds, int index) {
		PossibleResult result = new PossibleResult(getMalusForGroup(sounds, index, sounds.length), EMPTY_INT_ARRAY);

		// return super early
		if (result.malus == 0) return result;

		for (int i = this.config.minGroupSize; i <= this.config.maxGroupSize; i++) {
			final int preferredIndex = index + getPreferredIndex(i - this.config.minGroupSize);

			if (preferredIndex > sounds.length) {
				continue;
			}

			final int firstGroupMalus = getMalusForGroup(sounds, index, preferredIndex);

			if (preferredIndex == sounds.length) {
				if (result.malus > firstGroupMalus) {
					result = new PossibleResult(firstGroupMalus, preferredIndex);
				}
			} else {
				final PossibleResult restGroupsMalus = groupWord(sounds, preferredIndex);

				if (restGroupsMalus != null) {
					final PossibleResult possibleResult = new PossibleResult(firstGroupMalus + restGroupsMalus.malus,
							concat(preferredIndex, restGroupsMalus.groupSeparatorIndexes));

					if (result.malus > possibleResult.malus) {
						result = possibleResult;
					}
				}
			}

			// return early
			if (result.malus == 0) return result;
		}
		return result;
	}

	private static int[] concat(int indexZero, int[] indexRest) {
		final int[] result = new int[indexRest.length + 1];
		result[0] = indexZero;
		System.arraycopy(indexRest, 0, result, 1, indexRest.length);
		return result;
	}

	int getPreferredIndex(int i) {
		return this.preferredIndexMap[i];
	}

	int getMalusForGroup(PhoneticSound[] sounds, int startIndex, int endIndex) {
		int malus = 0;
		// first base on length
		final int length = endIndex - startIndex;
		if (length < this.config.minGroupSize) {
			malus = MALUS_TOO_SMALL;
		} else if (length > this.config.maxGroupSize) {
			malus = MALUS_TOO_BIG;
		} else {
			malus = Math.abs(length - this.config.preferredGroupSize);
		}
		// else based on anything else
		if (endIndex != sounds.length && !sounds[endIndex - 1].isValidEnd()) {
			malus += MALUS_NO_END;
		}
		return malus;
	}

	public int getPreferredGroupSize() {
		return this.config.getPreferredGroupSize();
	}

	public WordGrouper preferredGroupSize(int newPreferredGroupSize) {
		setPreferredGroupSize(newPreferredGroupSize);
		return this;
	}

	public void setPreferredGroupSize(int preferredGroupSize) {
		this.config.setPreferredGroupSize(preferredGroupSize);
		recalculatePreferredIndexMap();
	}

	public int getMaxGroupSize() {
		return this.config.getMaxGroupSize();
	}

	public WordGrouper maxGroupSize(int newMaxGroupSize) {
		setMaxGroupSize(newMaxGroupSize);
		return this;
	}

	public void setMaxGroupSize(int maxGroupSize) {
		this.config.setMaxGroupSize(maxGroupSize);
		recalculatePreferredIndexMap();
	}

	public int getMinGroupSize() {
		return this.config.getMinGroupSize();
	}

	public WordGrouper minGroupSize(int newMinGroupSize) {
		setMinGroupSize(newMinGroupSize);
		return this;
	}

	public void setMinGroupSize(int minGroupSize) {
		this.config.setMinGroupSize(minGroupSize);
		recalculatePreferredIndexMap();
	}

	public WordGrouperConfig getConfig() {
		return this.config;
	}

	public WordGrouper config(WordGrouperConfig newConfig) {
		setConfig(newConfig);
		return this;
	}

	public void setConfig(WordGrouperConfig config) {
		Objects.requireNonNull(config);
		this.config = config;
		recalculatePreferredIndexMap();
	}

	@Override
	public String toString() {
		return "WordGrouper [config=" + this.config + "]";
	}

	/**
	 * @since 0.4.0
	 */

	class PossibleResult {

		int[] groupSeparatorIndexes;
		int malus;

		public PossibleResult(int malus, int... groupSeparatorIndexes) {
			super();
			this.groupSeparatorIndexes = groupSeparatorIndexes;
			this.malus = malus;
		}
	}
}

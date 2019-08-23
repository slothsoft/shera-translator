package de.slothsoft.shera;

/**
 * Collects all configurations for a {@link WordGrouper}.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.4.0
 */

public class WordGrouperConfig {

	int preferredGroupSize = 4;
	int minGroupSize = 2;
	int maxGroupSize = 5;

	public int getMaxGroupSize() {
		return this.maxGroupSize;
	}

	public WordGrouperConfig maxGroupSize(int newMaxGroupSize) {
		setMaxGroupSize(newMaxGroupSize);
		return this;
	}

	public void setMaxGroupSize(int maxGroupSize) {
		this.maxGroupSize = maxGroupSize;
	}

	public int getMinGroupSize() {
		return this.minGroupSize;
	}

	public WordGrouperConfig minGroupSize(int newMinGroupSize) {
		setMinGroupSize(newMinGroupSize);
		return this;
	}

	public void setMinGroupSize(int minGroupSize) {
		this.minGroupSize = minGroupSize;
	}

	public int getPreferredGroupSize() {
		return this.preferredGroupSize;
	}

	public WordGrouperConfig preferredGroupSize(int newPreferredGroupSize) {
		setPreferredGroupSize(newPreferredGroupSize);
		return this;
	}

	public void setPreferredGroupSize(int preferredGroupSize) {
		this.preferredGroupSize = preferredGroupSize;
	}

	@Override
	public String toString() {
		return "WordGrouperConfig [preferredGroupSize=" + this.preferredGroupSize + ", minGroupSize="
				+ this.minGroupSize + ", maxGroupSize=" + this.maxGroupSize + "]";
	}

}

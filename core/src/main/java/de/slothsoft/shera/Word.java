package de.slothsoft.shera;

/**
 * This class wraps an array of {@link PhoneticSound} that represents a word. Might
 * contain additional information later.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.2.0
 */

public class Word {

	PhoneticSound[] content;
	int[] groupSeparatorIndexes;

	public Word(PhoneticSound... content) {
		setContent(content);
	}

	public PhoneticSound[] getContent() {
		return this.content;
	}

	public Word content(PhoneticSound[] newContent) {
		setContent(newContent);
		return this;
	}

	public void setContent(PhoneticSound[] content) {
		this.content = content;
	}

	/**
	 * @since 0.4.0
	 */

	public int getGroupCount() {
		return isIndexArrayBlank() ? 1 : this.groupSeparatorIndexes.length + 1;
	}

	private boolean isIndexArrayBlank() {
		return this.groupSeparatorIndexes == null || this.groupSeparatorIndexes.length == 0;
	}

	/**
	 * @since 0.4.0
	 */

	public PhoneticSound[] generateGroup(int group) {
		if (group < 0) throw new IllegalArgumentException("Group index must be postive!");
		if (isIndexArrayBlank()) {
			if (group > 0)
				throw new IllegalArgumentException("There is only 1 group, so index " + group + " was not found!");
			return this.content;
		}

		final int groupCount = getGroupCount();
		if (group >= groupCount)
			throw new IllegalArgumentException(
					"There are only " + groupCount + " groups, so index " + group + " was not found!");

		final int startIndex = group == 0 ? 0 : this.groupSeparatorIndexes[group - 1];
		final int endIndex = group == groupCount - 1 ? this.content.length : this.groupSeparatorIndexes[group];
		final int resultLength = endIndex - startIndex;
		final PhoneticSound[] result = new PhoneticSound[resultLength];
		for (int i = startIndex; i < endIndex; i++) {
			result[i - startIndex] = this.content[i];
		}
		return result;
	}

	/**
	 * @since 0.4.0
	 */

	public int[] getGroupSeparatorIndexes() {
		return this.groupSeparatorIndexes;
	}

	/**
	 * @since 0.4.0
	 */

	public Word groupSeparatorIndexes(int[] newGroupSeparatorIndexes) {
		setGroupSeparatorIndexes(newGroupSeparatorIndexes);
		return this;
	}

	/**
	 * @since 0.4.0
	 */

	public void setGroupSeparatorIndexes(int[] groupSeparatorIndexes) {
		this.groupSeparatorIndexes = groupSeparatorIndexes;
	}

}

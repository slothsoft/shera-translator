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

	public Word(PhoneticSound[] content) {
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

}

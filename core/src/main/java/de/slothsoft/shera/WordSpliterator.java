package de.slothsoft.shera;

import java.util.ArrayList;
import java.util.List;

/**
 * This class splits words into approximate sounds.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public class WordSpliterator {

	private final SoundMapper mapper;
	private final List<String> supportedLetters;

	public WordSpliterator(SoundMapper mapper) {
		this.mapper = mapper;
		this.supportedLetters = new ArrayList<>(mapper.getSupportedLetters());
		// this sorts by length of the letters (e.g. "sch" before "s" or "c")
		this.supportedLetters.sort((o1, o2) -> o2.length() - o1.length());
	}

	public PhoneticSound[] split(String word) {
		final String usedWord = word.toLowerCase();
		final List<PhoneticSound> result = new ArrayList<>();

		int index = 0;
		while (index < usedWord.length()) {
			final String substring = index == 0 ? usedWord : usedWord.substring(index);
			boolean foundLetter = false;

			for (final String supportedLetter : this.supportedLetters) {
				if (substring.startsWith(supportedLetter)) {
					// we know that letter(s)
					for (final PhoneticSound sound : this.mapper.getSounds(supportedLetter)) {
						result.add(sound);
					}
					index += supportedLetter.length();
					foundLetter = true;
					break;
				}
			}
			if (!foundLetter) {
				// we don't know the letter: just ignore
				index++;
			}
		}
		return result.toArray(new PhoneticSound[result.size()]);
	}

	public SoundGrouping group(PhoneticSound[] sounds) {

		return null;
	}

}

package de.slothsoft.shera;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import de.slothsoft.shera.mapper.EnglishSoundMapper;
import de.slothsoft.shera.mapper.GermanSoundMapper;

/**
 * The {@link PhoneticSound}s are nice, but they only work for English. So this mapper
 * maps other "letters" or syllables to the standard phonetic sounds.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public interface SoundMapper {

	static List<SoundMapper> createAll() {
		final List<SoundMapper> result = new ArrayList<>();
		result.add(new EnglishSoundMapper());
		result.add(new GermanSoundMapper());
		return result;
	}

	/**
	 * Returns all supported "letters" or syllables for this mapper. Elements must be
	 * lower case and not null.
	 *
	 * @return a set of letters; never null
	 */

	Set<String> getSupportedLetters();

	/**
	 * Returns the standard phonetic sound for a letter that was returned by
	 * {@link #getSupportedLetters()}.
	 *
	 * @return an array of {@link PhoneticSound}s; never null or empty
	 */

	PhoneticSound[] getSounds(String letter);

	/**
	 * The locale of the sound mapper. Is English on default.
	 *
	 * @return a locale; never null
	 */

	default Locale getLocale() {
		return Locale.ENGLISH;
	}
}

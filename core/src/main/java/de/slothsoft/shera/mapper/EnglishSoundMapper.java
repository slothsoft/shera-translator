package de.slothsoft.shera.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.SoundMapper;

/**
 * This is the default sound mapper that just uses the enum {@link PhoneticSound} "as is"
 * with some additions to support all letters.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public class EnglishSoundMapper implements SoundMapper {

	private final Map<String, PhoneticSound> mapping = new HashMap<>();

	public EnglishSoundMapper() {
		for (final PhoneticSound sound : PhoneticSound.values()) {
			this.mapping.put(sound.name().toLowerCase(), sound);
		}
		// these are missing, so I'm approximating them
		this.mapping.put("c", PhoneticSound.K);
		this.mapping.put("q", PhoneticSound.K);
		this.mapping.put("x", null); // the only multi sound letter
	}

	@Override
	public String getDisplayName() {
		return "English";
	}

	@Override
	public Set<String> getSupportedLetters() {
		return this.mapping.keySet();
	}

	@Override
	public PhoneticSound[] getSounds(String letter) {
		if ("x".equals(letter)) return new PhoneticSound[]{PhoneticSound.K, PhoneticSound.S};
		return new PhoneticSound[]{this.mapping.get(letter)};
	}

	@Override
	public String toString() {
		return "EnglishSoundMapper [" + this.mapping.size() + " letters]";
	}

}

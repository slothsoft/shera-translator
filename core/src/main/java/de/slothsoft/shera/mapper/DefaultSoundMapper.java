package de.slothsoft.shera.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.SoundMapper;

/**
 * This is the default sound mapper that just uses a {@link Map}. For testing mostly.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public class DefaultSoundMapper implements SoundMapper {

	private final Map<String, PhoneticSound> mapping;

	public DefaultSoundMapper(Map<String, PhoneticSound> mapping) {
		this.mapping = new HashMap<>(mapping);
	}

	public void addMapping(String letter, PhoneticSound sound) {
		this.mapping.put(letter, sound);
	}

	public void removeMapping(String letter) {
		this.mapping.remove(letter);
	}

	@Override
	public String getLocale() {
		return "xx";
	}

	@Override
	public Set<String> getSupportedLetters() {
		return this.mapping.keySet();
	}

	@Override
	public PhoneticSound[] getSounds(String letter) {
		return new PhoneticSound[]{this.mapping.get(letter)};
	}

}

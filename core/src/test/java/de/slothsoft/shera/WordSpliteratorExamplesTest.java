package de.slothsoft.shera;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.shera.mapper.EnglishSoundMapper;
import de.slothsoft.shera.mapper.GermanSoundMapper;

public class WordSpliteratorExamplesTest {

	// These are canon! Dreamworks said so!
	final static PhoneticSound[] SOUNDS_SHERA = {PhoneticSound.SH, PhoneticSound.EE, PhoneticSound.R, PhoneticSound.AH};
	final static PhoneticSound[] SOUNDS_SHADOW = {PhoneticSound.SH, PhoneticSound.A, PhoneticSound.D, PhoneticSound.OW};
	// manually translated
	final static PhoneticSound[] SOUNDS_STEFFI_DE = {PhoneticSound.SH, PhoneticSound.T, PhoneticSound.E,
			PhoneticSound.F, PhoneticSound.F, PhoneticSound.EE};

	// she-ra

	@Test
	public void testSplitSheRa() throws Exception { // we kinda have to
		final WordSpliterator spliterator = new WordSpliterator(new EnglishSoundMapper());

		// close enough I guess
		final PhoneticSound[] actualSounds = spliterator.split("Shee-Rah");

		Assert.assertArrayEquals(Arrays.toString(actualSounds), SOUNDS_SHERA, actualSounds);
	}

	@Test
	public void testSplitSheRaGerman() throws Exception { // we kinda have to
		final WordSpliterator spliterator = new WordSpliterator(new GermanSoundMapper());

		// close enough I guess
		final PhoneticSound[] actualSounds = spliterator.split("Shih-Rah");

		Assert.assertArrayEquals(Arrays.toString(actualSounds), SOUNDS_SHERA, actualSounds);
	}

	// shadow

	@Test
	public void testSplitShadow() throws Exception {
		final WordSpliterator spliterator = new WordSpliterator(new EnglishSoundMapper());

		// close enough I guess
		final PhoneticSound[] actualSounds = spliterator.split("Shadow");

		Assert.assertArrayEquals(Arrays.toString(actualSounds), SOUNDS_SHADOW, actualSounds);
	}

	// steffi

	@Test
	public void testSplitSteffi() throws Exception {
		final WordSpliterator spliterator = new WordSpliterator(new GermanSoundMapper());

		// close enough I guess
		final PhoneticSound[] actualSounds = spliterator.split("Steffie");

		Assert.assertArrayEquals(Arrays.toString(actualSounds), SOUNDS_STEFFI_DE, actualSounds);
	}
}
